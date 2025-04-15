package files.integrations

import scalatags.Text.all.*
import utils.Imports.*
object SharafFramework extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Sharaf")
      .withLabel("Sharaf")
      .withDescription("Sharaf - Hepek integrations")

  override def blogSettings =
    super.blogSettings.withSections(pfSection)

  val pfSection = Section(
    "Quick start",
    frag(
      s"""
        Hepek is supported natively in Sharaf.
        
        Examples:
        - [fullstack example](https://github.com/sake92/sharaf/tree/main/examples/fullstack)
        - [petclinic implementation](https://github.com/sake92/sharaf-petclinic)

      """.md
    )
  )
}
