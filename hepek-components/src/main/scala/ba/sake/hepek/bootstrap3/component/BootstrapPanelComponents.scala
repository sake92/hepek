package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.PanelComponents

object BootstrapPanelComponents {
  trait Type extends PanelComponents.Type

  object Type {
    case object Default extends Type { override def classes = List("panel-default") }
    case object Primary extends Type { override def classes = List("panel-primary") }
    case object Success extends Type { override def classes = List("panel-success") }
    case object Info    extends Type { override def classes = List("panel-info")    }
    case object Warning extends Type { override def classes = List("panel-warning") }
    case object Danger  extends Type { override def classes = List("panel-danger")  }
  }
}

case class BootstrapPanelComponents() extends PanelComponents {
  import BootstrapPanelComponents._

  val Companion = BootstrapPanelComponents

  override type PanelType = Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ) =
    div(cls := "panel", panelType.classes.map(cls := _))(
      header.map(h => div(cls := "panel-heading")(h)),
      div(cls := "panel-body")(body),
      footer.map(f => div(cls := "panel-footer")(f))
    )
}
