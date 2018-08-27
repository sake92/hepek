package ba.sake.hepek

import scalatags.Text.all.Frag

package object implicits {

  private val bc = ba.sake.hepek.html.component.BasicComponents

  val Section = ba.sake.hepek.html.structure.blog.Section
  type Section = ba.sake.hepek.html.structure.blog.Section

  implicit class HepekStringOps(str: String) {
    def md: Frag = bc.md(str)
  }
}
