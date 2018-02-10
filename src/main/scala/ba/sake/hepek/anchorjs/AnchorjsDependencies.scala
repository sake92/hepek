package ba.sake.hepek.anchorjs

import ba.sake.hepek.html.structure._

trait AnchorjsDependencies extends PageDependencies {

  def anchorjsVersion: String = "4.1.0"

  def anchorjsJSDependencies: List[String] = List(
    anchorjsDepsProvider.depPath(
      Dependency("anchor.min.js", anchorjsVersion, "anchor-js")
    )
  )

  def anchorjsDepsProvider = DependencyProvider.cdnjs

  abstract override def scriptURLs = super.scriptURLs ++ anchorjsJSDependencies

}
