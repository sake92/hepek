package files.hepek.components

import ba.sake.hepek.html.statik.BlogPostPage

trait HepekComponentsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index,
    reference.Index
  )

  override def pageCategory = Some(Index.pageSettings.label)

  override def currentCategoryPage = Some(Index)
}
