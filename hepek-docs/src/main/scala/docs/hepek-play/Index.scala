package docs.hepek.play

import scalatags.Text.all._
import utils.Imports.Bundle._

object Index extends HepekPlayDocsPage {

  override def pageSettings: PageSettings =
    super.pageSettings
      .withTitle("Hepek Play")
      .withDescription("Hepek Play")

  override def blogSettings: BlogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection: Section = Section(
    "Hepek Play",
    frag(
      s"""
        Hepek Play integrates Hepek components with Play framework.  
        [Here](${utils.links.PlayExampleUrl}) is an example to get you started.

        It contains basic machinery needed to render Hepek's `HtmlPage`.  
        This enables you to use Hepek's higher level abstractions or plain old Scalatags HTML.

      """.md
    )
  )
}
