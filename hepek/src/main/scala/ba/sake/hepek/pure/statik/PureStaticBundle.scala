package ba.sake.hepek.pure.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.pure.PureBundle

trait PureStaticBundle extends StaticBundle with PureBundle {

  override type StaticPage = PureStaticPage
}
