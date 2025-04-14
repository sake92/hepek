package files

import ba.sake.hepek.fusejs.FusejsIndex
import ba.sake.hepek.html.statik.StaticPage

object SearchIndex extends FusejsIndex {

  override def indexedPages: Seq[StaticPage] = Seq(Index) ++
    files.hepek.components.Index.categoryPosts ++
    files.hepek.Index.categoryPosts ++
    files.integrations.Index.categoryPosts

}
