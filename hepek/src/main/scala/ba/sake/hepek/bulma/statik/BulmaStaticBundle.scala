package ba.sake.hepek.bulma.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.bulma.BulmaBundle

trait BulmaStaticBundle extends StaticBundle with BulmaBundle {
  override type StaticPage = BulmaStaticPage
}
