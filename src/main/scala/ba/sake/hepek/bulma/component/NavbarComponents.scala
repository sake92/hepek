package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{Active, BulmaElement, BulmaModifier, EmptyAttribute, Transparent}
import scalatags.Text
import scalatags.Text.all._

object NavbarComponents extends NavbarComponents

trait NavbarElement extends BulmaElement

case class NavbarHamburger(active: Boolean = false) extends NavbarElement {
  override def content =
    a(
      cls := enrichCssClass("navbar-burger", isActive(active)),
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
  override def content: Text.all.Frag =
    a(cls := enrichCssClasses("navbar-item", modifiers))(elements)
}

case class DivNavbarItem(modifiers: BulmaModifier*)(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag =
    div(cls := enrichCssClasses("navbar-item", modifiers))(elements)
}
case class PlainAnchorNavbarItem(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = a(cls := "navbar-item")(elements)
}

case class PlainDivNavbarItem(elements: Frag*) extends NavbarItem {
  override def content: Text.all.Frag = div(cls := "navbar-item")(elements)
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

case class NavbarMenu(active: Boolean = false)(startItems: NavbarItem*)(endItems: NavbarItem*)
    extends NavbarElement {
  override def content: Text.all.Frag = div(cls := enrichCssClass("navbar-menu", isActive(active)))(
    div(cls := "navbar-start")(startItems.map(_.content)),
    div(cls := "navbar-end")(endItems.map(_.content))
  )
}

trait NavbarComponents {
  def transparentNavbar(elements: NavbarElement*) = buildNavbar(Transparent, elements: _*)
  def navbar(elements: NavbarElement*)            = buildNavbar(EmptyAttribute, elements: _*)

  private def buildNavbar(modifier: BulmaModifier, elements: NavbarElement*) =
    tag("nav")(
      cls := enrichCssClass("navbar", modifier),
      role := "navigation",
      aria.label := "main navigation"
    )(
      elements.map(_.content)
    )
}
