package files.hepek

import utils.Imports.Bundle.Navbar

trait HepekSsgDocsPage extends templates.HepekDocsAbstractPage {

  override def navbar = Some(Navbar.withActiveUrl(files.hepek.Index.ref))

  override def categoryPosts = List(
    Index,
    tutorials.Index,
    reference.Index
  )

  override def pageCategory = Some("Hepek SSG")
}
