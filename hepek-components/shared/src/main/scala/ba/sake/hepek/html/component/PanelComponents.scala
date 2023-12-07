package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

trait PanelComponents:

  type PanelType

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): Frag
