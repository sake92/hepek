package ba.sake.hepek.bulma.component

import scalatags.Text.all._

object MenuComponents extends MenuComponents

trait MenuComponents {
  def menuHeader(content: Frag*) = div(cls := "menu")(content)

  def menuList(items: Frag*) =
    ul(cls := "menu-list")(for {
      item <- items
    } yield li(item))

  def menuLabel(text: String) = p(cls := "menu-label", text)
}
