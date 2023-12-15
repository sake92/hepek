package files.integrations

import utils.Imports.Bundle.Navbar

trait HepekIntegrationsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    SharafFramework,
    PlayFramework,
    Http4s,
    ZIO
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def navbar = Some(Navbar.withActiveUrl(Index.ref))
}
