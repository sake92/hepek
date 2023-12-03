package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

class PlayBundle(
    val Bundle: Bundle
) {
  val PF: PlayForm = PlayForm(Bundle.Form)

  def withBundle(bundle: Bundle) = new PlayBundle(Bundle = bundle)
}
