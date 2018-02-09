package ba.sake.hepek.jquery

import ba.sake.hepek.html.structure._

trait JQueryDependencies extends PageDependencies {

  def jQueryVersion: String     = "3.2.1"
  def jQueryUseWebjars: Boolean = false

  def jQueryJSDependencies: List[String] = List(
    jQueryDepsProvider.depPath(
      Dependency("jquery.min.js", jQueryVersion, "jquery", Option("dist/"))
    )
  )

  def jQueryDepsProvider = DependencyProvider.unpkg

  abstract override def scriptURLs = super.scriptURLs ++ jQueryJSDependencies

}
