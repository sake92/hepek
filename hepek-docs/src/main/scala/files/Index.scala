package files

import utils.Imports.Bundle.*, Tags.*, Grid.*, Classes.*

object Index extends templates.HepekDocsStaticPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Welcome!")
      .withDescription("Hepek docs")

  override def pageContent =
    frag(
      div(cls := "page-header", txtAlignCenter)(
        h1("Welcome!")
      ),
      row(
        s"""
          Hepek is a collection of useful projects for typesafe HTML construction:
          - [Components](${hepek.components.Index.ref})
          - [Static site generator](${hepek.Index.ref})

          Components can be used in **any project**. This project has minimal dependencies.  
          If you need grid, form inputs, panels, navbars, markdown, code highlighting, maths rendering and lots more, 
          this is the library you're looking for.  
          It supports frameworks like Bootstrap, Bulma etc.  
          You write generic code, and it *compiles to Bootstrap*!  
          You can easily switch to a different framework and *override* parts you don't like. :)

          SSG has support for automatic relative links, PDF rendering and lots more.
        """.md
      ),
      super.pageContent
    )
}
