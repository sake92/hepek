package docs

import scalatags.Text.all._
import utils._, Imports._

object Bundle extends templates.HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Bundle",
    frag(
      """
        The concept of bundle is simple. 
        It contains all the needed components, abstractions provided by a CSS framework.

        Recommended way is to create an `Imports` object which extends one of predefined bundles.  
        Currently we only support Bootstrap 3 and PureCSS. More to come!
      """.md,
      chl.scala("""
        package utils

        object Imports extends BootstrapBundle
      """),
      """
        Then you `import utils.Imports._` in every other page or components you make.  
        This provides you the flexibility of changing your framework with just one line! Amazing, right!? :D

        Every `Bundle` contains traits like `StaticPage`, `Grid`, `Form` etc.  
        Your code should stay the same no matter which framework you use! :)
      """.md
    )
  )
}
