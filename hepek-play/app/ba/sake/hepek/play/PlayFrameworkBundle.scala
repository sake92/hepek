package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

final class PlayFrameworkBundle private (
    val Bundle: Bundle
) {
  val PFF: PlayFrameworkForm = PlayFrameworkForm(Bundle.Form)

  def withBundle(bundle: Bundle): PlayFrameworkBundle = new PlayFrameworkBundle(bundle)
}

object PlayFrameworkBundle:
  def apply(Bundle: Bundle) = new PlayFrameworkBundle(Bundle)
