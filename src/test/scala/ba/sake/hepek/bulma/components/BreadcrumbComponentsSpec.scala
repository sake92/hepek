package ba.sake.hepek.bulma.components

import ba.sake.hepek.bulma.component.BreadcrumbComponents
import ba.sake.hepek.matchers.HepekMatchers
import org.scalatest.{FlatSpec, Matchers}
import scalatags.Text
import scalatags.Text.all._

object BreadcrumbComponentPage extends BreadcrumbComponents {

  def simpleContent: Text.all.Frag =
    breadcrumb(
      div(
        p("This is a big paragraph of text")
      )
    )

  def centeredContent: Text.all.Frag =
    centeredBreadcrumb(
      div(
        p("This is a big paragraph of text")
      )
    )
}

class BreadcrumbComponentsSpec extends FlatSpec with Matchers with HepekMatchers {
  "simple Breadcrumb" should "have class 'breadcrumb'" in {
    BreadcrumbComponentPage.simpleContent.toString shouldBe
      """<nav class="breadcrumb"><ul><li><div><p>This is a big paragraph of text</p></div></li></ul></nav>"""
  }

  "centered Breadcrumb" should "have class 'breadcrumb is-centered'" in {
    BreadcrumbComponentPage.centeredContent.toString shouldBe
      """<nav class="breadcrumb is-centered"><ul><li><div><p>This is a big paragraph of text</p></div></li></ul></nav>"""
  }

}
