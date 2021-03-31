package docs.hepek

import ba.sake.hepek.html.statik.BlogPostPage

trait HepekDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index,
    QuickStart,
    StaticBundle,
    StaticPage,
    BlogPage,
    MultiPage,
    PdfGenerator
  )

  override def pageCategory = Some(Index.pageSettings.label)
}
