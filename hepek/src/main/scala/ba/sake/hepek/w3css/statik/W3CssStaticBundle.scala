package ba.sake.hepek.w3css.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.w3css.W3CssBundle

trait W3CssStaticBundle extends StaticBundle with W3CssBundle {
  override type StaticPage = W3CssStaticPage
}
