package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle

trait PlayBundle extends Bundle {

  val hf = new HepekPlayForm {
    val fc = Form
  }
}
