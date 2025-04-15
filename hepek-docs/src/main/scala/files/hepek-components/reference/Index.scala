package files.hepek.components.reference

import scalatags.Text.all.*
import utils.Imports.*

object Index extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Components Reference")
      .withLabel("Reference")
      .withDescription("Hepek Components Reference")

  override def blogSettings = super.blogSettings.withSections(
    Section(
      "Reference Guide",
      frag(
        s"""
        Hepek Components have a concept of [Bundle](${BundleReference.ref}), 
        which is a collection of framework-specific components like grid, form, navbar, etc.  
        These components are interface based so you can easily swap one framework instead of other (e.g. from Bootstrap to Bulma).

      """.md
        // TODO dodat next / prev
      )
    )
  )

}
