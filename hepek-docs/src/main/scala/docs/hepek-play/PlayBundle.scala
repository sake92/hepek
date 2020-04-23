package docs.hepek.play

import scalatags.Text.all._
import utils.Imports.Bundle._
import utils.Imports._

object PlayBundle extends HepekPlayDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Play bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Play bundle",
    frag(
      """
      You use `Bundle` in the same way as in the hepek-components!

      The `PlayBundleImpl` contains Play-specific helpers, like forms.
      """.md,
      chl.scala("""
      package utils

      import ba.sake.hepek.play.PlayBundle
      import ba.sake.hepek.bootstrap3.BootstrapBundle

      object Imports {
        val Bundle = BootstrapBundle()
        val PlayBundle = PlayBundleImpl(Bundle)
      }
      """),
      """
      `PlayBundle` adds a new helper called  `HPF`, short for "Hepek Play Form".
      """.md
    )
  )
}
