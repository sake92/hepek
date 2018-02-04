package ba.sake.hepek.mathjax

import ba.sake.hepek.html.structure.PageDependencies

trait MathjaxDependencies extends PageDependencies {

  def mathjaxVersion: String = "2.7.2"

  def mathjaxConfig: String = "AM_CHTML"

  def mathjaxJSDependencies: List[String] =
    List(s"$cdnJSURL/mathjax/$mathjaxVersion/MathJax.js?config=$mathjaxConfig")

  abstract override def scriptURLs = super.scriptURLs ++ mathjaxJSDependencies

}
