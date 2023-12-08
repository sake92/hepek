package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

final class PlayFrameworkBundle[B <: Bundle] private (val Bundle: B):

  val PF: PlayFrameworkForm = PlayFrameworkForm(Bundle.Form)

  def withBundle[B2 <: Bundle](bundle: B2): PlayFrameworkBundle[B2] =
    new PlayFrameworkBundle(bundle)

object PlayFrameworkBundle:
  def apply[B <: Bundle](bundle: B) = new PlayFrameworkBundle(bundle)
