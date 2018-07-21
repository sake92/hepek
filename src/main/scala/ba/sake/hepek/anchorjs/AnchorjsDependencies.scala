package ba.sake.hepek.anchorjs

import ba.sake.hepek.html.structure._

/** You need to provide Anchorjs config by yourself.
  * @see https://www.bryanbraun.com/anchorjs/#basic-usage
  */
trait AnchorjsDependencies extends PageDependencies {

  def anchorjsVersion: String                  = "4.1.0"
  def anchorjsDepsProvider: DependencyProvider = DependencyProvider.cdnjs

  def anchorjsJSDependencies: List[String] = List(
    anchorjsDepsProvider.depPath(
      Dependency("anchor.min.js", anchorjsVersion, "anchor-js")
    )
  )

  override def scriptURLs = super.scriptURLs ++ anchorjsJSDependencies
}
