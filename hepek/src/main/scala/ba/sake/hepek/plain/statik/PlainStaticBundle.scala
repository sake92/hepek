package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainBundle

trait PlainStaticBundle extends StaticBundle with PlainBundle {
  override type StaticPage = PlainStaticPage
}
