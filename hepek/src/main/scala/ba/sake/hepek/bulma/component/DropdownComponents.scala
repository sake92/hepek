package ba.sake.hepek.bulma.component

import scalatags.Text.all._
import ba.sake.hepek.bulma.{BulmaElement}

object DropdownComponents extends DropdownComponents

case class DropdownTrigger(triggerLabel: String) extends BulmaElement {

  override def content =
    div(cls := "dropdown-trigger ")(
      button(cls := "button ", aria.haspopup := true, aria.controls := "dropdown-menu")(
        span(triggerLabel),
        span(cls := "icon is-small ")(
          i(cls := "fas fa-angle-down ", aria.hidden := true)
        )
      )
    )
}

trait DropdownElement extends BulmaElement

case class DropdownItem(elements: Frag) extends DropdownElement {
  override def content = a(cls := "dropdown-item ")(elements)
}

case object DropdownDivider extends DropdownElement {
  override def content = hr(cls := "dropdown-divider ")
}

case class DropdownMenu(elements: DropdownElement*) extends BulmaElement {

  override def content = div(cls := "dropdown-menu ", role := "menu")(
    div(cls := "dropdown-content ")(for {
      elem <- elements
    } yield elem.content)
  )
}

trait DropdownComponents {

  def dropdown(trigger: Option[DropdownTrigger] = None, menu: DropdownMenu) =
    div(cls := "dropdown ")(
      optionalElementContent(trigger),
      menu.content
    )
}
