package ba.sake.hepek.bootstrap3.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.bootstrap3.BootstrapBundle

object BootstrapStaticBundle extends BootstrapStaticBundle

trait BootstrapStaticBundle extends StaticBundle with BootstrapBundle {
  override type StaticPage = BootstrapStaticPage
}
