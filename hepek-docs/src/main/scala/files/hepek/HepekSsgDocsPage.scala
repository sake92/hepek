package files.hepek

import utils.Imports.Bundle.Navbar

trait HepekSsgDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    tutorials.Index,
    reference.Index
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def navbar = Some(Navbar.withActiveUrl(Index.ref))
}
