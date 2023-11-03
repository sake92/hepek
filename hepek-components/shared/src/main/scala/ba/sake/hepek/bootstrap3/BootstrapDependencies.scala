package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html._
import ba.sake.hepek.jquery.JQueryDependencies

trait BootstrapDependencies extends JQueryDependencies {

  def bootstrapSettings: ComponentSettings =
    ComponentSettings("3.4.1", "bootstrap", DependencyProvider.unpkg)

  def bootstrapDependencies: ComponentDependencies =
    ComponentDependencies()
      .withJsDependencies(
        Dependencies().withDeps(
          Dependency(
            "js/bootstrap.min.js",
            bootstrapSettings.version,
            bootstrapSettings.pkg,
            baseFolder = Option("dist/")
          )
        )
      )
      .withCssDependencies(
        Dependencies().withDeps(
          Dependency(
            "css/bootstrap.min.css",
            bootstrapSettings.version,
            bootstrapSettings.pkg,
            baseFolder = Option("dist/")
          )
        )
      )

  override def components =
    super.components.appended(bootstrapSettings -> bootstrapDependencies)
}
