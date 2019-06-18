package docs.hepek.components

import scalatags.Text.all._
import utils._, Imports._

object Bundle extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Bundle",
    frag(
      """
        Bundle contains all the needed components provided by a CSS/JS framework.  
        Currently, supported frameworks are Bootstrap 3, Bulma and PureCSS.

        Recommended usage is to create an `Imports` object which extends one of the predefined bundles:
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap3.BootstrapBundle

        object Imports extends BootstrapBundle
      """),
      """
        Then you `import utils.Imports._` in every other page/template you make.  
        This provides you with flexibility of changing your framework with just one line! Amazing, right!? :D

        Every `Bundle` contains traits like `HtmlPage`, `Grid`, `Form`, `Classes` etc.  
        Your code will stay the same no matter which framework you use! :)
      """.md
    )
  )
}
