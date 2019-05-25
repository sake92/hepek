package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.component.BootstrapComponentsBundle
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle

trait BootstrapBundle extends Bundle with BootstrapComponentsBundle {

  type ClassesBundle = BootstrapClassesBundle
  val classes = BootstrapClassesBundle

  override type HtmlPage = BootstrapPage
}
