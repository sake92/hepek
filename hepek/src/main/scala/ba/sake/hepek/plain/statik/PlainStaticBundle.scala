package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainBundle

object PlainStaticBundle extends PlainStaticBundle

trait PlainStaticBundle extends StaticBundle with PlainBundle {
  override type StaticPage = PlainStaticPage
}
