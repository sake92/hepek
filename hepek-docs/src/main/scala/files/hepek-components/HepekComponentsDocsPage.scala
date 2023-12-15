package files.hepek.components

import ba.sake.hepek.html.statik.BlogPostPage
import utils.Imports.Bundle.Navbar

trait HepekComponentsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index,
    reference.Index
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def navbar = Some(Navbar.withActiveUrl(Index.ref))
}
