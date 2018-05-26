package hepek.utils

import ba.sake.hepek.html.structure.blog.BlogPostPage
import docs._

object links {

  private val hepekUrl =
    "https://github.com/sake92/hepek/blob/master/src/main/scala/ba/sake/hepek"
  private val hepekExamplesUrl =
    "https://github.com/sake92/hepek-examples/blob/master/examples"

  val StarterProjectURL = "https://github.com/sake92/hepek-starter"

  val StaticPageUrl       = hepekUrl + "/html/structure/StaticPage.scala"
  val BlogPostPageUrl     = hepekUrl + "/html/structure/blog/BlogPostPage.scala"
  val PageDependenciesUrl = hepekUrl + "/html/structure/PageDependencies.scala"
  // bootstrap
  val BootstrapGridComponentsUrl = hepekUrl + "/bootstrap3/component/BootstrapGridComponents.scala"
  // prismJs
  val PrismCodeHighlightComponentsUrl = hepekUrl + "/prismjs/PrismCodeHighlightComponents.scala"
  val PrismJSExampleUrl               = hepekExamplesUrl + "/prismjs/src/main/scala/example/PrismJSExample.scala"
  // prismJs
  val KatexDependenciesUrl = hepekUrl + "/katex/KatexDependencies.scala"

}
