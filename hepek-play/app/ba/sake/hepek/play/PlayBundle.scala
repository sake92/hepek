package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

case class PlayBundleImpl(
    val Bundle: Bundle
) {
  val HPF: HepekPlayForm = HepekPlayForm(Bundle.Form)

  def withBundle(bundle: Bundle) = copy(Bundle = bundle)
}
