package docs.hepek.components

import scalatags.Text.all._
import utils.Imports._

object Index extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek components")
      .withDescription("Hepek components")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek components",
    frag(
      s"""
          Hepek components contain the core components of Hepek SSG and Hepek Play.  
          These are mostly wrappers around Scalatags.  

          Components are interface-based so we can compose them easily and replace with no effort.  
          In a nutshell, this means you can easily swap Bootstrap3 backend with Bulma, and vice versa.  
          This is achieved just by extending a different `trait`!

          In order to group these, Hepek has a concept of `Bundle`.  
          Every framework fills it with its own implementations of components.  
          Proceed to [next page](${Bundle.ref}) for more detailed explanation.
  
        """.md
    )
  )

}
