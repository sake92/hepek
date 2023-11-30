package files.hepek.components

import utils.Imports._
import utils.Imports.Bundle.*, Tags.*

object BundleDocs extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Bundle",
    frag(
      """
        Bundle contains all needed components provided by a HTML/CSS framework.  
        Currently, supported frameworks are Bootstrap5, Bootstrap3, Bulma.

        Recommended usage is to create an `Imports` object which contains one of the predefined bundles:
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap5.BootstrapBundle

        object Imports {
          val Bundle = BootstrapBundle()
        }
      """),
      """
        Then you `import utils.Imports.Bundle._` in every other page/template you make.  
        This provides you with flexibility of changing your framework with just one line! Amazing, right!? :D

        Every `Bundle` contains traits like `Page`, `Grid`, `Form`, `Classes`, `Tags` etc.  
        Your code will stay the ~same no matter which framework you use! :)

        You can **customize** the `Bundle` and its components with corresponding `with*` methods!
      """.md
    )
  )
}
