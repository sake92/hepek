package files

import ba.sake.hepek.fusejs.FusejsIndex
import ba.sake.hepek.html.statik.StaticPage
import utils.Site

object SearchIndex extends FusejsIndex {

  override def indexedPages: Seq[StaticPage] =
    Site.allSearchIndexedPages

}
