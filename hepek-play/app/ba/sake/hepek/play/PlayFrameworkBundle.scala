package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

class PlayFrameworkBundle(
    val Bundle: Bundle
) {
  val PFF: PlayFrameworkForm = PlayFrameworkForm(Bundle.Form)

  def withBundle(bundle: Bundle): PlayFrameworkBundle = new PlayFrameworkBundle(bundle)
}
