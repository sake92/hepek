package ba.sake.hepek.play

import ba.sake.stone.Wither
import ba.sake.hepek.html.Bundle

@Wither
case class PlayBundleImpl(
    val Bundle: Bundle
) {
  val HPF: HepekPlayForm = HepekPlayForm(Bundle.Form)
}
