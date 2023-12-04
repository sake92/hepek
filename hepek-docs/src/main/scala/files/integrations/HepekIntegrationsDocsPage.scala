package files.integrations

import files.hepek.HepekDocsPage

trait  HepekIntegrationsDocsPage extends HepekDocsPage {
  override def categoryPosts = List(
    Index,
    SharafFramework,
    PlayFramework
  )

  override def pageCategory = Some(Index.pageSettings.label)
}

