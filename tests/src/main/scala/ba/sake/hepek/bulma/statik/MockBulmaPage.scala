package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.EmptyAttribute
import ba.sake.hepek.bulma.component.{
  CardComponents,
  CardContent,
  NavbarComponents,
  PlainAnchorNavbarItem
}
import scalatags.Text
import scalatags.Text.all._

object MockBulmaPage extends BulmaStaticPage {

  def contentData =
    CardContent(
      div(p("hello")),
      ul(
        li("one"),
        li("two")
      )
    )

  val navBar = NavbarComponents.navbar(
    PlainAnchorNavbarItem("test-1"),
    PlainAnchorNavbarItem("test-2")
  )

  val card = CardComponents.card(
    None,
    None,
    contentData,
    None
  )

  override def pageContent: Text.all.Frag = frag(
    navBar,
    container(EmptyAttribute)(card)
  )

}
