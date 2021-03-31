package ba.sake.hepek.fontawesome5

import ba.sake.hepek.html._

trait FADependencies extends PageDependencies {

  def faSettings: ComponentSettings =
    ComponentSettings("5.11.2", "font-awesome", DependencyProvider.cdnjs)

  def faDependencies: ComponentDependencies =
    ComponentDependencies()
      .withJsDependencies(
        Dependencies().withDeps(
          Dependency("js/all.js", faSettings.version, faSettings.pkg)
        )
      )
      .withCssDependencies(
        Dependencies().withDeps(
          Dependency("css/all.css", faSettings.version, faSettings.pkg)
        )
      )

  override def components =
    super.components :+ (faSettings, faDependencies)
}
