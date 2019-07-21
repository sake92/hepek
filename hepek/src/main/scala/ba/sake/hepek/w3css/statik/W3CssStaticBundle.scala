package ba.sake.hepek.bootstrap3.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.bootstrap3.BootstrapBundle

trait W3CssStaticBundle extends StaticBundle with W3CssBundle {

  override type StaticPage = BootstrapStaticPage
}
