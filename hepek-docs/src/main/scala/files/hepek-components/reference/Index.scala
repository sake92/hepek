package files.hepek.components.reference

import utils.Imports.Bundle.*, Tags.*

object Index extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Components reference")
      .withLabel("Reference")
      .withDescription("Hepek Components reference")

  override def blogSettings = super.blogSettings.withSections(
    Section(
      "Reference guide",
      frag(
        s"""
        Nitty-gritty technical descriptions of how Hepek Components works.  
        Detailed information about Hepek Components API.

      """.md
        // TODO dodat next / prev
      )
    )
  )

}
