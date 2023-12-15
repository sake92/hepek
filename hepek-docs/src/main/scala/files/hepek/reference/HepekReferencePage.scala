package files.hepek.reference

import files.hepek.HepekSsgDocsPage

trait HepekReferencePage extends HepekSsgDocsPage {

  override def categoryPosts = List(
    Index,
    StaticBundle,
    StaticPage,
    BlogPage,
    MultiPage
  )
}
