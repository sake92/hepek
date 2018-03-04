package hepek.utils

import hepek.templates.HepekDocsPage
import docs._

object Site {

  val url  = "http://sake92.github.io/hepek"
  val name = "hepek"

  def pages: List[HepekDocsPage] = List(
    QuickStart,
    CodeWalkthrough
  )

}
