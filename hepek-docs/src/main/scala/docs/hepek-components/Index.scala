package docs.hepek.components

import ba.sake.hepek.scalatags.all._
import utils.Imports.Bundle._

object Index extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Components")
      .withDescription("Hepek Components")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek Components",
    frag(
      s"""
          Hepek Components contain the core components of Hepek SSG and Hepek Play.  
          These are mostly wrappers around Scalatags.  

          Components are *interface-based* so we can compose them easily and replace with no effort.  
          In a nutshell, this means you can **choose HTML framework implementation**: Bootstrap3/Bulma/W3Css.  

          In order to group components, Hepek has a concept of `Bundle`.  
          Every framework fills it with its own implementations of components.  
          Proceed to [next page](${Bundle.ref}) for more detailed explanation.
  
        """.md
    )
  )
}
