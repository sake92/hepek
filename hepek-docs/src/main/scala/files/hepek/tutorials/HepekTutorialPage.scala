package files.hepek.tutorials

import files.hepek.HepekSsgDocsPage

trait HepekTutorialPage extends HepekSsgDocsPage {

  override def categoryPosts = List(
    Index,
    QuickStart,
    GhPagesDeployment,
    PdfGenerator
  )

  override def pageCategory = Some("hepek-tutorial")
}
