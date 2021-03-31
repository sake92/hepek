package docs

import java.time.LocalDate

import ba.sake.hepek.scalatags.all._
import templates.HepekDocsStaticPage
import utils.Imports.Bundle._

import Classes._

object Index extends HepekDocsStaticPage {
  private val ratios = Ratios(Ratio(1, 4, 1), Ratio(1, 1), Ratio(1, 4, 1))

  private val grid = Grid.withScreenRatios(
    Grid.screenRatios.withSm(None).withXs(None).withLg(ratios).withMd(ratios)
  )
  import grid._

  override def pageSettings =
    super.pageSettings
      .withTitle("Welcome!")
      .withDescription("Hepek docs")

  val currYear = LocalDate.now()

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
          - [Play framework integration](${hepek.play.Index.ref})

          Components can be used in **any project**. It only depends on Scalatags and Commonmark.  
          If you need typesafe grid, form inputs, panels, navbars, markdown, maths and lots more, 
          this is the library you're looking for.

          SSG has support for automatic relative links, PDF rendering and lots more.

          Hepek Play seamlessly integrates components with Play framework.
        """.md
      ),
      super.pageContent
    )
}
