package files.integrations

import scalatags.Text.all.*
import utils.Imports.*

object Index extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Integrations")
      .withDescription("Hepek Integrations")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek Integrations",
    frag(
      s"""
        Use Hepek Components in your favorite framework:
        - [Sharaf](${SharafFramework.ref})
        - [Play Framework](${PlayFramework.ref})
        - [Http4s](${Http4s.ref})
        - [ZIO](${ZIO.ref})
      """.md
    )
  )
}
