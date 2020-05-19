package ba.sake.hepek.bulma.component

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.PanelComponents

object BulmaPanelComponents {
  trait Type extends PanelComponents.Type

  object Type {
    case object Default extends Type { override def classes = List("is-primary") }
    case object Primary extends Type { override def classes = List("is-primary") }
    case object Success extends Type { override def classes = List("is-success") }
    case object Info    extends Type { override def classes = List("is-info")    }
    case object Warning extends Type { override def classes = List("is-warning") }
    case object Danger  extends Type { override def classes = List("is-danger")  }
  }
}

case class BulmaPanelComponents() extends PanelComponents {
  import BulmaPanelComponents._

  val Companion = BulmaPanelComponents

  override type PanelType = Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ) =
    div(cls := "panel", panelType.classes.map(cls := _))(
      header.map(h => div(cls := "panel-heading")(h)),
      div(cls := "panel-block")(body),
      footer.map(f => div(cls := "panel-block")(f))
    )
}
