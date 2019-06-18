package docs.hepek.play

import scalatags.Text.all._
import utils.Imports._

object Index extends HepekPlayDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Play")
      .withDescription("Hepek Play")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek Play",
    frag(
      s"""
        Hepek Play integrates Hepek components with Play framework.

        TODO

      """.md
    )
  )

}
