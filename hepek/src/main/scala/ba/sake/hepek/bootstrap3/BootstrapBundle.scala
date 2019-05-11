package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.component.BootstrapComponentsBundle
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage

trait BootstrapBundle extends Bundle with BootstrapComponentsBundle {

  override type StaticPage = BootstrapStaticPage
}
