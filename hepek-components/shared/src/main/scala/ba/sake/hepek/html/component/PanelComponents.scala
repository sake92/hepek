package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

object PanelComponents {

  trait Type {
    def classes: List[String] = List.empty
  }
}

trait PanelComponents {
  import PanelComponents._

  type PanelType <: Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): Frag
}
