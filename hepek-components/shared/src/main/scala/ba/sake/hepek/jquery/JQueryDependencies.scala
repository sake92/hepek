package ba.sake.hepek.jquery

import ba.sake.hepek.html._

trait JQueryDependencies extends PageDependencies {

  def jQuerySettings: ComponentSettings =
    ComponentSettings("3.6.0", "jquery")

  def jQueryDependencies = ComponentDependencies.default.withJsDependencies(
    Dependencies.default.withDeps(
      Dependency(
        "jquery.min.js",
        jQuerySettings.version,
        jQuerySettings.pkg
      ).withBaseFolder("dist/")
    )
  )

  override def components =
    super.components.appended(jQuerySettings -> jQueryDependencies)
}
