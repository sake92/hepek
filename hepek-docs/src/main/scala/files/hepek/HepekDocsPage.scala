package files.hepek

trait HepekDocsPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    tutorials.Index,
    reference.Index
  )

  override def pageCategory = Some("hepek-docs")
}
