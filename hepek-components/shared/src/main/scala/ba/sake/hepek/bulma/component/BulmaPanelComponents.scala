package ba.sake.hepek.bulma.component

import ba.sake.hepek.html.component.PanelComponents
import ba.sake.hepek.scalatags.all.*

final class BulmaPanelComponents private () extends PanelComponents {

  val Companion = BulmaPanelComponents

  override type PanelType = BulmaPanelComponents.Type

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

object BulmaPanelComponents:

  val default: BulmaPanelComponents = new BulmaPanelComponents()

  enum Type(val classes: Seq[String]):
    case Default extends Type(Seq("is-primary"))
    case Primary extends Type(Seq("is-primary"))
    case Success extends Type(Seq("is-success"))
    case Info    extends Type(Seq("is-info"))
    case Warning extends Type(Seq("is-warning"))
    case Danger  extends Type(Seq("is-danger"))
