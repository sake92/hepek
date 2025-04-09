package ba.sake.nodejs.script.executor

import scala.util.Properties

object NodejsScriptExecutor {
  private val npmExe  = if Properties.isWin then "npm.cmd" else "npm"
  private val nodeExe = if Properties.isWin then "node.exe" else "node"

  // absolute_path -> environment
  private var environments = Map[String, Environment]()

  private var installedDepsCache = Map[String, Set[NpmDependency]]()

  def execute(
      env: Environment,
      jsScript: String,
      dependencies: Set[NpmDependency],
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

  case class Environment(folder: os.Path) {
    def key: String = folder.toNIO.toAbsolutePath.toString
  }

  case class NpmDependency(name: String, version: Option[String] = None) {
    def asNpmString: String = name + version.map("@" + _).getOrElse("")
  }
}
