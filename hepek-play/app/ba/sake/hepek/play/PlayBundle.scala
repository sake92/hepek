package ba.sake.hepek.play

import play.api.i18n.Messages
import ba.sake.stone.Wither
import ba.sake.hepek.html.Bundle

case class PlayBundleImpl(
    val Bundle: Bundle
)(implicit playMsgs: Messages) {
  val HPF: HepekPlayForm = HepekPlayForm(Bundle.Form)

  def withBundle(bundle: Bundle) = copy(Bundle = bundle)
}
