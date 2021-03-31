package docs.hepek.components

import ba.sake.hepek.html.statik.BlogPostPage

trait HepekComponentsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index,
    Bundle,
    HtmlPage,
    Dependencies,
    GridSupport,
    CodeHighlighter,
    MathSupport
  )

  override def pageCategory = Some(Index.pageSettings.label)
}
