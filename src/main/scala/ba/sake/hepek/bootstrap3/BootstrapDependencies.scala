package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.jquery.JQueryDependencies
import ba.sake.hepek.html.structure.Dependency

trait BootstrapDependencies extends PageDependencies with JQueryDependencies {

  def bootstrapVersion: String     = "3.3.7"
  def bootstrapUseWebjars: Boolean = false

  def bootstrapCSSDependencies: List[String] =
    List(
      dependencyProvider.depPath(
        Dependency("css/bootstrap.min.css", bootstrapVersion, "bootstrap")
      )
    )

  def bootstrapJSDependencies: List[String] =
    List(
      dependencyProvider.depPath(
        Dependency("js/bootstrap.min.js", bootstrapVersion, "bootstrap")
      )
    )

  abstract override def styleURLs  = super.styleURLs ++ bootstrapCSSDependencies
  abstract override def scriptURLs = super.scriptURLs ++ bootstrapJSDependencies

}
