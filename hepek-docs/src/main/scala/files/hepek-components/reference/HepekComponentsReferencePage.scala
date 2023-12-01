package files.hepek.components.reference

trait HepekComponentsReferencePage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    BundleReference,
    HtmlPage,
    Dependencies,
    GridReference,
    CodeHighlighter,
    MathReference
  )

  override def pageCategory = Some("hepek-components-reference")
}
