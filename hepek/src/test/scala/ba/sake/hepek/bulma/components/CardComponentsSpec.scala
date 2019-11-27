package ba.sake.hepek.bulma.components

import ba.sake.hepek.bulma.component._
import ba.sake.hepek.matchers.HepekMatchers
import org.scalatest.{FlatSpec, Matchers}
import scalatags.Text.all._

object BulmaCardPage extends CardComponents {
  def header = DefaultHeader("Component")
  def image  = CardImage("path.png")
  def footer = CardFooter(p("element"), p("element2"))

  def contentData =
    CardContent(
      div(p("pippo")),
      ul(
        li("one"),
        li("two")
      )
    )
  def cardExample = card(Some(header), Some(image), contentData, Some(footer))
}

class CardComponentsSpec extends FlatSpec with Matchers with HepekMatchers {
  "simple Card" should "have the following structure" in {
    BulmaCardPage.cardExample.toString shouldBe
      """<div class="card ">
        |<header class="card-header "><p class="card-header-title ">Component</p></header>
        |<div class="card-image "><figure class="image is-4by3 "><img src="path.png" /></figure></div>
        |<div class="card-content "><div><p>pippo</p></div><ul><li>one</li><li>two</li></ul></div>
        |<footer class="card-footer ">
        |<a class="card-footer-item "><p>element</p></a>
        |<a class="card-footer-item "><p>element2</p></a>
        |</footer></div>""".stripMargin
        .replaceAll("\n", "")
  }
}
