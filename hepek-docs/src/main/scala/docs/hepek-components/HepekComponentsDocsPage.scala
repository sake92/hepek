package files.hepek.components

import ba.sake.hepek.html.statik.BlogPostPage

trait HepekComponentsDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts: List[BlogPostPage] = List(
    Index,
    BundleDocs,
    HtmlPage,
    Dependencies,
    GridSupport,
    CodeHighlighter,
    MathSupport
  )

  override def pageCategory = Some(Index.pageSettings.label)
}
