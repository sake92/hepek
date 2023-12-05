package files.integrations

import utils.Imports.Bundle.Navbar

trait HepekIntegrationsDocsPage extends templates.HepekDocsAbstractPage {

  override def navbar = Some(Navbar.withActiveUrl(files.integrations.Index.ref))

  override def categoryPosts = List(
    Index,
    SharafFramework,
    PlayFramework
  )

  override def pageCategory = Some(Index.pageSettings.label)
}
