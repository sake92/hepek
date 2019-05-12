package utils

import ba.sake.hepek.html.statik.BlogPostPage
import docs._

object Site {

  val url  = "http://sake92.github.io/hepek"
  val name = "hepek"

  def pages: List[BlogPostPage] = List(
    QuickStart,
    Bundle,
    StaticPage,
    BlogPage,
    Layout,
    Dependencies,
    CodeHighlighter,
    MathSupport,
    PdfGenerator,
    CodeWalkthrough
  )

}
