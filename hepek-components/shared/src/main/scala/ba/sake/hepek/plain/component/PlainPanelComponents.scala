package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.PanelComponents
import ba.sake.hepek.scalatags.all._

final class PlainPanelComponents private () extends PanelComponents {

  val Companion = PlainPanelComponents

  override type PanelType = PlainPanelComponents.Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ) =
    div(cls := "pnl", panelType.classes.map(cls := _))(
      header.map(h => div(cls := "pnl-heading")(h)),
      div(cls := "pnl-body")(body),
      footer.map(f => div(cls := "pnl-footer")(f))
    )
}

object PlainPanelComponents:

  val default: PlainPanelComponents = new PlainPanelComponents()

  enum Type(val classes: Seq[String]):
    case Default extends Type(Seq("pnl-default"))
    case Primary extends Type(Seq("pnl-primary"))
    case Success extends Type(Seq("pnl-success"))
    case Info    extends Type(Seq("pnl-info"))
    case Warning extends Type(Seq("pnl-warning"))
    case Danger  extends Type(Seq("pnl-danger"))
