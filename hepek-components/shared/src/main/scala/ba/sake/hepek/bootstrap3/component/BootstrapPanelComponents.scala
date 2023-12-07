package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.PanelComponents
import ba.sake.hepek.scalatags.all._

final class BootstrapPanelComponents() extends PanelComponents {

  val Companion = BootstrapPanelComponents

  override type PanelType = BootstrapPanelComponents.Type

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

object BootstrapPanelComponents:

  enum Type(val classes: Seq[String]):
    case Default extends Type(Seq("panel-default"))
    case Primary extends Type(Seq("panel-primary"))
    case Success extends Type(Seq("panel-success"))
    case Info    extends Type(Seq("panel-info"))
    case Warning extends Type(Seq("panel-warning"))
    case Danger  extends Type(Seq("panel-danger"))
