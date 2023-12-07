package ba.sake.hepek.pure

import ba.sake.hepek.html.*

trait PureDependencies extends PageDependencies {

  def pureModules: List[String] =
    List("pure", "buttons", "forms", "grids-responsive", "menus", "tables")

  def pureSettings: ComponentSettings =
    ComponentSettings("1.0.0", "purecss").withDepsProvider(DependencyProvider.unpkg)

  def pureDependencies = ComponentDependencies.default.withCssDependencies(
    Dependencies.default.withDeps(
      pureModules.map { moduleName =>
        Dependency(
          s"$moduleName-min.css",
          pureSettings.version,
          pureSettings.pkg
        ).withBaseFolder("dist/")
      }
    )
  )

  override def components = super.components.appended(pureSettings -> pureDependencies)
}
