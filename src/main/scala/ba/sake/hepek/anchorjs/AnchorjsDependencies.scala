package ba.sake.hepek.anchorjs

import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.html.structure.Dependency

trait AnchorjsDependencies extends PageDependencies {

  def anchorjsVersion: String     = "4.1.0"
  def anchorjsUseWebjars: Boolean = false

  def anchorjsJSDependencies: List[String] = List(
    dependencyProvider.depPath(
      Dependency("anchor.min.js", anchorjsVersion, "anchor-js")
    )
  )

  abstract override def scriptURLs = super.scriptURLs ++ anchorjsJSDependencies

}
