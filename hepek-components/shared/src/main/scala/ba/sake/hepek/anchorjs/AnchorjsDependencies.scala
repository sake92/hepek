package ba.sake.hepek.anchorjs

import ba.sake.hepek.html.*

/** @see
  *   https://www.bryanbraun.com/anchorjs/#basic-usage
  */
trait AnchorjsDependencies extends PageDependencies {

  def anchorjsSettings: ComponentSettings =
    ComponentSettings("4.1.0", "anchor-js")

  def anchorjsDependencies: ComponentDependencies =
    ComponentDependencies.empty.withJsDependencies(
      Dependencies.empty.withDeps(
        Dependency("anchor.min.js", anchorjsSettings.version, anchorjsSettings.pkg)
      )
    )

  override def components =
    super.components.appended(anchorjsSettings -> anchorjsDependencies)
}
