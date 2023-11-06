package ba.sake.hepek.bootstrap5

import ba.sake.hepek.html._

trait BootstrapDependencies extends PageDependencies {

  def bootstrapSettings: ComponentSettings =
    ComponentSettings("5.3.2", "bootstrap", DependencyProvider.unpkg)

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
