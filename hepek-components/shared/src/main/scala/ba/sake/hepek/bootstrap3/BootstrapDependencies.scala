package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.*
import ba.sake.hepek.jquery.JQueryDependencies

trait BootstrapDependencies extends JQueryDependencies {

  def bootstrapSettings: ComponentSettings =
    ComponentSettings("3.4.1", "bootstrap").withDepsProvider(DependencyProvider.unpkg)

  def bootstrapDependencies: ComponentDependencies =
    ComponentDependencies.empty
      .withJsDependencies(
        Dependencies.empty.withDeps(
          Dependency(
            "js/bootstrap.min.js",
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
