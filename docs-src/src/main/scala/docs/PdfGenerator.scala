package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import hepek.templates.HepekDocsPage
import hepek.utils._, Imports._

object PdfGenerator extends HepekDocsPage {

  override def pageTitle = "Pdf generator"

  override def postSections = List(
    pdfGeneratorSection
  )

  /* CONTENT */
  val pdfGeneratorSection = Section(
    "Pdf generator",
    frag(
      md(s"""
        TODO
      """)
    )
  )

}
