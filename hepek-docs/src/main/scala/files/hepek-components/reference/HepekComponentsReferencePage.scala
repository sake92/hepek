package files.hepek.components.reference

import files.hepek.components.HepekComponentsDocsPage

trait HepekComponentsReferencePage extends HepekComponentsDocsPage {

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
