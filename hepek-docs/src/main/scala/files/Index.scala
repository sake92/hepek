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
          Hepek is a collection of useful projects for typesafe HTML construction in Scala:
          - [Components](${hepek.components.Index.ref})
          - [Static site generator](${hepek.Index.ref})

          Components can be used in any project, has minimal dependencies.  
          It provides utilites for grid, form inputs, panels, navbars, markdown, code highlighting, maths rendering and lots more.  
          It supports frameworks like Bootstrap 5, Bulma etc.  
          You can switch to a different framework with minimal effort and override parts you don't like.

          SSG has support for automatic relative links, PDF rendering and lots more.
        """.md
      ),
      super.pageContent
    )
}
