package files.integrations

trait HepekIntegrationsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    SharafFramework,
    PlayFramework,
    Http4s,
    ZIO
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def currentCategoryPage = Some(Index)
}
