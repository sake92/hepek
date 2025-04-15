package ba.sake.nodejs.script.executor

import java.security.MessageDigest
import scala.util.Properties
import NodejsScriptExecutor.*

import java.util.Base64

/** Executes a Node.js script in a given folder (sandbox) with optional npm dependencies.
  * @param baseFolder
  *   Base folder for the script execution.
  * @param dependencies
  *   List of npm dependencies to install before executing the script.
  */
class NodejsScriptExecutor(
    baseFolder: os.Path,
    dependencies: Seq[NpmDependency] = Seq.empty
) {
  initialize()

  private def getMd5B64(str: String): String = {
    val bytesOfMessage = str.getBytes("UTF-8")
    val md             = MessageDigest.getInstance("MD5")
    val theMD5digest   = md.digest(bytesOfMessage)
    val b64            = Base64.getEncoder.encode(theMD5digest)
    new String(b64, "UTF-8").replace('/', '-').replace('=', '_').replace('+', '$')
  }

  private def initialize(): Unit = {
    val envKey = baseFolder.toNIO.toAbsolutePath.toString
    if !os.exists(baseFolder) then os.makeDir.all(baseFolder)

    for dep <- dependencies do {
      val installedDeps = installedDepsCache.getOrElse(envKey, Set.empty)
      if !installedDeps(dep) then {
        os.call((npmExe, "install", dep.asNpmString), cwd = baseFolder)
        installedDepsCache += envKey -> (installedDeps + dep)
      }
    }
  }

  /** Executes a Node.js script in the given folder.
    * @param jsScript
    *   JavaScript code to be executed.
    * @param scriptName
    *   Name of the script file to be created.
    * @return
    *   stdout of the script
    */
  def executeScript(jsScript: String, scriptName: String = "script.js"): String = {
    val scriptMd5            = getMd5B64(jsScript)
    val cachedResultFileName = baseFolder / "cached-results" / s"$scriptMd5.txt"
    if os.exists(cachedResultFileName) then {
      os.read(cachedResultFileName)
    } else {
      val scriptFile = baseFolder / scriptName
      os.write.over(scriptFile, jsScript)
      val res = os.call((nodeExe, scriptFile), cwd = baseFolder)
      // remove ANSI codes from output https://stackoverflow.com/a/14652763/4496364
      val finalResult = res.out.text().replaceAll("\u001B\\[[;\\d]*m", "")
      // cache the result
      os.write.over(cachedResultFileName, finalResult, createFolders = true)
      finalResult
    }
  }
}

object NodejsScriptExecutor {
  private val npmExe  = if Properties.isWin then "npm.cmd" else "npm"
  private val nodeExe = if Properties.isWin then "node.exe" else "node"

  // baseFolder -> installed dependencies
  private var installedDepsCache = Map[String, Set[NpmDependency]]()

  case class NpmDependency(name: String, version: Option[String] = None) {
    def asNpmString: String = name + version.map("@" + _).getOrElse("")
  }
}
