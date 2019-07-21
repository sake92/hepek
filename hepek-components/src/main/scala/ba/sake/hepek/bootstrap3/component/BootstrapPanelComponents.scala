package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.PanelComponents

object BootstrapPanelComponents extends BootstrapPanelComponents {

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

trait BootstrapPanelComponents extends PanelComponents {
  import BootstrapPanelComponents._

  override type PanelType = Type

  def panel(
      tpe: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ) =
    div(cls := "panel", tpe.classes)(
      div(cls := "panel-heading")(header),
      div(cls := "panel-body")(body),
      div(cls := "panel-footer")(footer)
    )
}
