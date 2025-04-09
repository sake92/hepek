package ba.sake.hepek.fontawesome5

import ba.sake.hepek.html.*

trait FADependencies extends PageDependencies {

  def faSettings: ComponentSettings =
    ComponentSettings("5.11.2", "font-awesome")

  def faDependencies: ComponentDependencies =
    ComponentDependencies.empty
      .withJsDependencies(
        Dependencies.empty.withDeps(
          Dependency("js/all.js", faSettings.version, faSettings.pkg)
        )
      )
      .withCssDependencies(
        Dependencies.empty.withDeps(
          Dependency("css/all.css", faSettings.version, faSettings.pkg)
        )
      )

  override def components =
    super.components.appended(faSettings -> faDependencies)
}
