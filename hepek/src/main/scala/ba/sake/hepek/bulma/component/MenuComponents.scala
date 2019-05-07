package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.BulmaElement
import scalatags.Text
import scalatags.Text.all._

object MenuComponents extends MenuComponents

trait MenuElement extends BulmaElement

case class MenuList(elements: Frag*) extends MenuElement {
  override def content: Text.all.Frag =
    ul(cls := "menu-list ")(for {
      item <- elements
    } yield li(item))
}

case class MenuLabel(label: String) extends MenuElement {
  override def content: Text.all.Frag = p(cls := "menu-label ", label)
}

trait MenuComponents {
  def menu(elements: MenuElement*) = div(cls := "menu ")(elements.map(_.content))
}
