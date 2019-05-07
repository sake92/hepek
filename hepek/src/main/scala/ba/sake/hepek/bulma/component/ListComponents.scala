package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{Active, BulmaElement, EmptyAttribute, Hoverable}
import scalatags.Text
import scalatags.Text.all._

object ListComponents extends ListComponents

trait ListComponents {

  def list(children: ListItem*) =
    div(cls := enrichCssClass("list", Hoverable))(children.map(_.content))

  def item(inner: Frag, to: Option[String] = None, selected: Boolean = false): ListItem =
    ListItem(inner, to, selected)

}

case class ListItem(inner: Frag, to: Option[String], selected: Boolean) extends BulmaElement {

  override def content: Frag = {
    val activeClass = if (selected) Active else EmptyAttribute
    val base        = a(cls := enrichCssClass("list-item", activeClass))
    val linked      = to.fold(base)(l => base(href := l))
    linked(inner)
  }

}
