package ba.sake.hepek.plain.component

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.PanelComponents

object PlainPanelComponents {
  trait Type extends PanelComponents.Type

  object Type {
    case object Default extends Type { override def classes: List[String] = List("pnl-default") }
    case object Primary extends Type { override def classes: List[String] = List("pnl-primary") }
    case object Success extends Type { override def classes: List[String] = List("pnl-success") }
    case object Info    extends Type { override def classes: List[String] = List("pnl-info")    }
    case object Warning extends Type { override def classes: List[String] = List("pnl-warning") }
    case object Danger  extends Type { override def classes: List[String] = List("pnl-danger")  }
  }
}

case class PlainPanelComponents() extends PanelComponents {
  import PlainPanelComponents._

  val Companion = PlainPanelComponents

  override type PanelType = Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): ba.sake.hepek.scalatags.TypedTag[org.scalajs.dom.html.Div] =
    div(cls := "pnl", panelType.classes.map(cls := _))(
      header.map(h => div(cls := "pnl-heading")(h)),
      div(cls := "pnl-body")(body),
      footer.map(f => div(cls := "pnl-footer")(f))
    )
}
