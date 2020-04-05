package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

trait PlayBundle extends Bundle {
  def HPF: HepekPlayForm
}

case class PlayBundleImpl(
    HPF: HepekPlayForm
)
