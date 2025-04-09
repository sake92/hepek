package ba.sake.hepek.htmx

import ba.sake.hepek.html.*

/** @see
  *   https://htmx.org/
  */
trait HtmxDependencies extends PageDependencies {

  def htmxSettings: ComponentSettings =
    ComponentSettings("1.9.10", "htmx.org").withDepsProvider(DependencyProvider.unpkg)

  def htmxDependencies: ComponentDependencies =
    ComponentDependencies.empty.withJsDependencies(
      Dependencies.empty.withDeps(
        Dependency("htmx.min.js", htmxSettings.version, htmxSettings.pkg).withBaseFolder("dist/")
      )
    )

  override def components =
    super.components.appended(htmxSettings -> htmxDependencies)
}
