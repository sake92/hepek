package ba.sake.hepek.w3css

import ba.sake.hepek.html._

trait W3CssDependencies extends PageDependencies {

  def w3cssSettings: ComponentSettings =
    ComponentSettings("4.1.0", "w3-css", DependencyProvider.unpkg)

  def w3cssDependencies: ComponentDependencies =
    ComponentDependencies()
      .withCssDependencies(
        Dependencies().withDeps(
          Dependency(
            "w3.css",
            w3cssSettings.version,
            w3cssSettings.pkg
          )
        )
      )

  override def components =
    super.components :+ (w3cssSettings, w3cssDependencies)
}
