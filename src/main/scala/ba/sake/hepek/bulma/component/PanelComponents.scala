package ba.sake.hepek.bulma.component

import scalatags.Text.all._
import ba.sake.hepek.bulma.{BulmaElement}

object PanelComponents extends PanelComponents

sealed trait PanelElement extends BulmaElement {
  def fragments: Seq[Frag]
}

case class PanelTab(fragments: Frag*) extends PanelElement {
  override def content = p(cls := "panel-tabs")(fragments)
}
case class PanelHeading(fragments: Frag*) extends PanelElement {
  override def content = p(cls := "panel-heading")(fragments)
}
case class PanelBlock(fragments: Frag*) extends PanelElement {
  override def content = div(cls := "panel-block")(fragments)
}

trait PanelComponents {

  def navPanel(heading: Option[PanelHeading], elements: PanelElement*) =
    heading match {
      case Some(head) => tag("nav")(cls := "panel")(head.content)(elements.map(x => x.content))
      case None       => tag("nav")(cls := "panel")(elements.map(x => x.content))
    }
}
