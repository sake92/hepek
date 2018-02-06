package ba.sake.hepek.jquery

import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.html.structure.Dependency

trait JQueryDependencies extends PageDependencies {

  def jQueryVersion: String     = "3.2.1"
  def jQueryUseWebjars: Boolean = false

  def jQueryJSDependencies: List[String] = List(
    dependencyProvider.depPath(
      Dependency("jquery.min.js", jQueryVersion, "jquery")
    )
  )

  abstract override def scriptURLs = super.scriptURLs ++ jQueryJSDependencies

}
