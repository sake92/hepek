package ba.sake.hepek.jquery

import ba.sake.hepek.html._

trait JQueryDependencies extends PageDependencies {

  def jQuerySettings: ComponentSettings =
    ComponentSettings("3.6.0", "jquery", DependencyProvider.cdnjs)

  def jQueryDependencies = ComponentDependencies().withJsDependencies(
    Dependencies().withDeps(
      Dependency(
        "jquery.min.js",
        jQuerySettings.version,
        jQuerySettings.pkg,
        baseFolder = Option("dist/")
      )
    )
  )

  override def components =
    super.components :+ (jQuerySettings, jQueryDependencies)
}
