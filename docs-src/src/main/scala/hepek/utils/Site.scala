package hepek.utils

import ba.sake.hepek.html.structure.blog.BlogPostPage
import docs._

object Site {

  val url  = "http://sake92.github.io/hepek"
  val name = "hepek"

  def pages: List[BlogPostPage] = List(
    QuickStart,
    StaticPage,
    BlogPage,
    CodeWalkthrough
  )

}
