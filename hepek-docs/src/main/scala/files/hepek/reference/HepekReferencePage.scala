package files.hepek.reference

trait HepekReferencePage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    StaticBundle,
    StaticPage,
    BlogPage,
    MultiPage
  )

  override def pageCategory = Some("hepek-reference")
}
