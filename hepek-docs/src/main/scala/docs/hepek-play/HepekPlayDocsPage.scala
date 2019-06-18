package docs.hepek.play

import ba.sake.hepek.html.statik.BlogPostPage

trait HepekPlayDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index
  )
}
