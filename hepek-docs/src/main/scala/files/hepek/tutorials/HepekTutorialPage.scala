package files.hepek.tutorials

trait HepekTutorialPage extends templates.HepekDocsAbstractPage {

  override def categoryPosts = List(
    Index,
    QuickStart,
    GhPagesDeployment,
    PdfGenerator
  )

  override def pageCategory = Some("hepek-tutorial")
}
