package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

trait PanelComponents:

  private[hepek] type PanelType

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): Frag
