package ba.sake.nodejs.script.executor

import scala.util.Properties

class NodejsScriptExecutor {
  // TODO odma u konstruktoru inicijalizovat deps itd.
}

object NodejsScriptExecutor {
  private val npmExe  = if Properties.isWin then "npm.cmd" else "npm"
  private val nodeExe = if Properties.isWin then "node.exe" else "node"

  // absolute_path -> environment
  private var environments = Map[String, Environment]()
  // environment -> installed dependencies
  private var installedDepsCache = Map[String, Set[NpmDependency]]()
  // TODO cache overall results on disk!!!

  /** @param env
    *   execution environment, a nodejs project folder/"sandbox"...
    * @param jsScript
    *   code to execute
    * @param dependencies
    *   npm dependencies to install
    * @param scriptName
    *   name of the script file to create
    * @return
    *   stdout of the script
    */
  def execute(
      env: Environment,
      jsScript: String,
      dependencies: Set[NpmDependency] = Set.empty,
      scriptName: String = "script.js"
  ): String = {
    environments += env.key -> env

    if !os.exists(env.folder) then os.makeDir.all(env.folder)

    val cwd        = env.folder
    val scriptFile = cwd / scriptName
    os.write.over(scriptFile, jsScript)

    for dep <- dependencies do {
      val installedDeps = installedDepsCache.getOrElse(env.key, Set.empty)
      if !installedDeps(dep) then {
        os.call((npmExe, "install", dep.asNpmString), cwd = cwd)
        installedDepsCache += env.key -> (installedDeps + dep)
      }
    }

    val res = os.call((nodeExe, scriptFile), cwd = cwd)
    // remove ANSI codes from output https://stackoverflow.com/a/14652763/4496364
    res.out.text().replaceAll("\u001B\\[[;\\d]*m", "")
  }

  // TODO remove and just use os.Path
  case class Environment(folder: os.Path) {
    def key: String = folder.toNIO.toAbsolutePath.toString
  }

  case class NpmDependency(name: String, version: Option[String] = None) {
    def asNpmString: String = name + version.map("@" + _).getOrElse("")
  }
}
