package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma._
import scalatags.Text.all._

object PaginationComponents extends PaginationComponents

sealed trait PaginationElement extends BulmaElement

case class Pagination(
    previous: Option[String] = Some("previous"),
    next: Option[String] = Some("next")
) extends PaginationElement {
  override def content = frag(
    previous.map(a(cls := "pagination-previous")(_)),
    next.map(a(cls := "pagination-next")(_))
  )
}

case object PaginationEllipsis extends PaginationElement {
  override def content = a(cls := "pagination-ellipsis ")(raw("&hellip;"))
}

case class PaginationNumber(number: Integer, isCurrent: Boolean = false) extends PaginationElement {
  override def content = {
    val current: BulmaModifier = if (isCurrent) Current else EmptyAttribute
    li(a(cls := enrichCssClass("pagination-link", current), number.toString()))
  }
}

case class PaginationList(elements: PaginationNumber*) extends PaginationElement {
  override def content = ul(cls := "pagination-list ")(elements.map(_.content))
}

trait PaginationComponents {

  def pagination(elements: PaginationElement*) =
    tag("nav")(cls := "pagination ", role := "navigation", aria.label := "pagination")(
      elements.map(_.content)
    )

}
