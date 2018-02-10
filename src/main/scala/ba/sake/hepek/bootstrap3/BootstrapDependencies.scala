package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.structure._
import ba.sake.hepek.jquery.JQueryDependencies

trait BootstrapDependencies extends PageDependencies with JQueryDependencies {

  def bootstrapVersion: String = "3.3.7"

  def bootstrapCSSDependencies: List[String] =
    List(
      bootstrapDepsProvider.depPath(
        Dependency("css/bootstrap.min.css",
                   bootstrapVersion,
                   "bootstrap",
                   Option("dist/"))
      )
    )

  def bootstrapJSDependencies: List[String] =
    List(
      bootstrapDepsProvider.depPath(
        Dependency("js/bootstrap.min.js",
                   bootstrapVersion,
                   "bootstrap",
                   Option("dist/"))
      )
    )

  def bootstrapDepsProvider = DependencyProvider.unpkg

  override def styleURLs  = super.styleURLs ++ bootstrapCSSDependencies
  override def scriptURLs = super.scriptURLs ++ bootstrapJSDependencies

}
