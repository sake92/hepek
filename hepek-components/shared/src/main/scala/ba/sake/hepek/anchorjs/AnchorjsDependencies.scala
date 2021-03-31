package ba.sake.hepek.anchorjs

import ba.sake.hepek.html._

/** You need to provide Anchorjs config by yourself.
  *
  * @see https://www.bryanbraun.com/anchorjs/#basic-usage
  */
trait AnchorjsDependencies extends PageDependencies {

  def anchorjsSettings: ComponentSettings =
    ComponentSettings("4.1.0", "anchor-js", DependencyProvider.cdnjs)

  def anchorjsDependencies: ComponentDependencies =
    ComponentDependencies().withJsDependencies(
      Dependencies().withDeps(
        Dependency("anchor.min.js", anchorjsSettings.version, anchorjsSettings.pkg)
      )
    )

  override def components: List[(BaseComponentSettings, ComponentDependencies)] =
    super.components :+ (anchorjsSettings, anchorjsDependencies)
}
