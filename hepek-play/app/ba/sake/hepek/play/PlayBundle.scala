package ba.sake.hepek.play

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.BootstrapBundle
import ba.sake.hepek.html.component.FormComponents

trait PlayBundle extends Bundle {

  val hf = new HepekPlayForm {
    override type FormImpl = Form
    val fc = Form
  }
}
