package ba.sake.hepek.html.component

import scalatags.Text.all, all._

object PanelComponents {
  trait Type { def classes: List[String] = List.empty }
}

trait PanelComponents {
  import PanelComponents._

  type PanelType <: Type

  def panel(
      tpe: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): Frag

}
