package ba.sake.hepek.mathjax

import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.html.structure.Dependency

trait MathjaxDependencies extends PageDependencies {

  def mathjaxVersion: String     = "2.7.2"
  def mathjaxUseWebjars: Boolean = false
  def mathjaxConfig: String      = "AM_CHTML"

  private def mathjaxJSDependencies: List[String] =
    List(
      dependencyProvider.depPath(
        Dependency("MathJax.js",
                   mathjaxVersion,
                   "mathjax",
                   Option(s"config=$mathjaxConfig"))
      )
    )

  abstract override def scriptURLs =
    super.scriptURLs ++ mathjaxJSDependencies

}
