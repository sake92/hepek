package ba.sake.hepek.fontawesome5

import ba.sake.hepek.html.*

trait FADependencies extends PageDependencies {

  def faSettings: ComponentSettings =
    ComponentSettings("5.11.2", "font-awesome")

  def faDependencies: ComponentDependencies =
    ComponentDependencies.default
      .withJsDependencies(
        Dependencies.default.withDeps(
          Dependency("js/all.js", faSettings.version, faSettings.pkg)
        )
      )
      .withCssDependencies(
        Dependencies.default.withDeps(
          Dependency("css/all.css", faSettings.version, faSettings.pkg)
        )
      )

  override def components =
    super.components.appended(faSettings -> faDependencies)
}
