package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{Active, BulmaElement, BulmaModifier, EmptyAttribute, Transparent}
import scalatags.Text
import scalatags.Text.all._

object NavbarComponents extends NavbarComponents

trait NavbarElement extends BulmaElement {
  def isActive(active: Boolean): BulmaModifier = if (active) Active else EmptyAttribute
}

case class NavbarHamburger(active: Boolean) extends NavbarElement {
  override def content =
    a(
      cls := s"navbar-burger${cssClass(isActive(active))}",
      role := "button",
      aria.label := "menu",
      aria.expanded := false
    )(
      span(aria.hidden := true),
      span(aria.hidden := true),
      span(aria.hidden := true)
    )
}

trait NavbarItem extends NavbarElement

case class AnchorNavbarItem(modifiers: BulmaModifier*)(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = a(cls := s"navbar-item${cssClasses(modifiers)}")(elements)
}

case class DivNavbarItem(modifiers: BulmaModifier*)(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = div(cls := s"navbar-item${cssClasses(modifiers)}")(elements)
}
case class PlainAnchorNavbarItem(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = a(cls := s"navbar-item")(elements)
}

case class PlainDivNavbarItem(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = div(cls := s"navbar-item")(elements)
}

case class NavbarBrand(hamburger: Option[NavbarHamburger], items: NavbarItem*)
    extends NavbarElement {
  override def content: Text.all.Frag = div(cls := "navbar-brand")(
    items.map(_.content),
    optionalElementContent(hamburger)
  )
}

case class NavbarDropdown(items: NavbarItem*) extends NavbarElement {
  override def content: Text.all.Frag = div(cls := "navbar-dropdown")(
    items.map(_.content)
  )
}

case class NavbarMenu(active: Boolean)(startItems: NavbarItem*)(endItems: NavbarItem*)
    extends NavbarElement {
  override def content: Text.all.Frag = div(cls := s"navbar-menu${cssClass(isActive(active))}")(
    div(cls := "navbar-start")(startItems.map(_.content)),
    div(cls := "navbar-end")(endItems.map(_.content))
  )
}

trait NavbarComponents {
  def transparentNavbar(elements: NavbarElement*) = buildNavbar(Transparent, elements: _*)
  def navbar(elements: NavbarElement*)            = buildNavbar(EmptyAttribute, elements: _*)

  private def buildNavbar(modifier: BulmaModifier, elements: NavbarElement*) =
    tag("nav")(
      cls := s"navbar${cssClass(modifier)}",
      role := "navigation",
      aria.label := "main navigation"
    )(
      elements.map(_.content)
    )
}
