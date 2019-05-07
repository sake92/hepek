package ba.sake.hepek.pure

package component

import scalatags.Text.all._
import ba.sake.hepek.html.component._

object PureMenuComponents extends PureMenuComponents {
  sealed trait Type {
    def classes: String = "pure-menu "
  }

  object Type {
    case object Vertical extends Type {
      // pure-menu-vertical is NOT FROM PURECSS, it's added to style the width of menu
      override def classes = super.classes + "pure-menu-vertical "
    }
    case object Horizontal extends Type {
      override def classes = super.classes + "pure-menu-horizontal "
    }
  }
}

trait PureMenuComponents extends LinkComponents {
  import PureMenuComponents._

  def menuType: Type = Type.Vertical

  def menu(attrs: AttrPair*)(content: Frag*): Frag =
    div(cls := menuType.classes, attrs)(content)

  /* top-level menu-list and its items */
  def menuList(attrs: AttrPair*)(content: Frag*): Frag =
    ul(cls := "pure-menu-list ", attrs)(content)

  def menuItem(attrs: AttrPair*)(content: Frag*): Frag =
    li(cls := "pure-menu-item ", attrs)(content)

  /* nested menu-list and its items */
  def menuItemWithChildren(attrs: AttrPair*)(content: Frag*): Frag = {
    val allAttrs = (cls := "pure-menu-has-children pure-menu-allow-hover ") +: attrs
    menuItem(allAttrs: _*)(content)
  }

  def menuChildren(attrs: AttrPair*)(content: Frag*): Frag =
    ul(cls := "pure-menu-children ", attrs)(content)

  def menuHeading(attrs: AttrPair*)(content: Frag*): Frag =
    span(cls := "pure-menu-heading ", attrs)(content)

  def menuLink(hreff: String, attrs: AttrPair*)(content: Frag*): Frag = {
    val allAttrs = (cls := "pure-menu-link ") +: attrs
    hyperlink(hreff, allAttrs: _*)(content)
  }

}
