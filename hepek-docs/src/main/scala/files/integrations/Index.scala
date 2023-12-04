package files.integrations

import utils.Imports.Bundle.*, Tags.*

object Index extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Integrations")
      .withDescription("Hepek Integrations")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )


  val introSection = Section(
    "Hepek Integrations",
    frag(
      s"""
        Use Hepek Components in your favorite framework:
          - [Sharaf](${SharafFramework.ref})
          - [Play Framework](${PlayFramework.ref})
        """.md
    )
  )
}
