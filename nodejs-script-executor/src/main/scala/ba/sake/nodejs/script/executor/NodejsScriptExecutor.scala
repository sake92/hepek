package ba.sake.nodejs.script.executor

import scala.util.Properties
import NodejsScriptExecutor.*

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

  // TODO cache overall results on disk!!! ???

  /** Executes a Node.js script in the given folder.
    * @param jsScript
    *   JavaScript code to be executed.
    * @param scriptName
    *   Name of the script file to be created.
    * @return
    *   stdout of the script
    */
  def executeScript(jsScript: String, scriptName: String = "script.js"): String = {
    val scriptFile = baseFolder / scriptName
    os.write.over(scriptFile, jsScript)
    val res = os.call((nodeExe, scriptFile), cwd = baseFolder)
    // remove ANSI codes from output https://stackoverflow.com/a/14652763/4496364
    res.out.text().replaceAll("\u001B\\[[;\\d]*m", "")
  }
}

object NodejsScriptExecutor {
  private val npmExe  = if Properties.isWin then "npm.cmd" else "npm"
  private val nodeExe = if Properties.isWin then "node.exe" else "node"

  // environment -> installed dependencies
  private var installedDepsCache = Map[String, Set[NpmDependency]]()

  case class NpmDependency(name: String, version: Option[String] = None) {
    def asNpmString: String = name + version.map("@" + _).getOrElse("")
  }
}
