package ba.sake.hepek.bootstrap5

import ba.sake.hepek.html.*

trait BootstrapDependencies extends PageDependencies {

  def bootstrapSettings: ComponentSettings =
    ComponentSettings("5.3.2", "bootstrap")

  def bootstrapDependencies: ComponentDependencies =
    ComponentDependencies.empty
      .withJsDependencies(
        Dependencies.empty.withDeps(
          Dependency(
            "js/bootstrap.bundle.min.js",
            bootstrapSettings.version,
            bootstrapSettings.pkg
          ).withBaseFolder("dist/")
        )
      )
      .withCssDependencies(
        Dependencies.empty.withDeps(
          Dependency(
            "css/bootstrap.min.css",
            bootstrapSettings.version,
            bootstrapSettings.pkg
          ).withBaseFolder("dist/")
        )
      )

  override def components =
    super.components.appended(bootstrapSettings -> bootstrapDependencies)
}
