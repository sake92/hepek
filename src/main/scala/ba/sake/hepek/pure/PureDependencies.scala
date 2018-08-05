package ba.sake.hepek.pure

import ba.sake.hepek.html.structure._

trait PureDependencies extends PageDependencies {

  def pureVersion: String                  = "1.0.0"
  def pureDepsProvider: DependencyProvider = DependencyProvider.unpkg

  def pureCSSDependencies: List[String] =
    List("pure", "buttons", "forms", "grids-responsive", "menus", "tables")
      .map { moduleName =>
        pureDepsProvider.depPath(
          Dependency(s"$moduleName-min.css",
                     pureVersion,
                     "purecss",
                     baseFolder = Option("build/"))
        )
      }

  def pureJSDependencies: List[String] =
    List(
      pureDepsProvider.depPath(
        Dependency("purecss", // TODO
                   pureVersion,
                   "purecss",
                   baseFolder = Option("build/"))
      )
    )

  override def styleURLs = super.styleURLs ++ pureCSSDependencies
  // override def scriptURLs = super.scriptURLs ++ pureJSDependencies
}
