package files.hepek

trait HepekSsgDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    tutorials.Index,
    reference.Index
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def currentCategoryPage = Some(Index)
}
