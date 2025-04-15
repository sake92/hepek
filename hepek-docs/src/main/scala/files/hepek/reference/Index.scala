package files.hepek.reference

import scalatags.Text.all.*
import utils.Imports.*

object Index extends HepekReferencePage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek SSG reference")
      .withLabel("Reference")
      .withDescription("Hepek SSG reference")

  override def blogSettings = super.blogSettings.withSections(
    Section(
      "Reference guide",
      frag(
        s"""
        Nitty-gritty technical descriptions of how Hepek SSG works.  
        Detailed information about Hepek's API.

      """.md
        // TODO dodat next / prev
      )
    )
  )

}
