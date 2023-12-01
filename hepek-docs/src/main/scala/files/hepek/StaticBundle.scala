package files.hepek

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object StaticBundle extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Static bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Static bundle",
    frag(
      """
        `StaticBundle` has components needed for workgin with static HTML.  
        It contains a trait `StaticPage` you need to extend in order to get the needed dependencies and setup.

        Recommended usage is to create an `Imports` object which contains one of the predefined bundles.  
        Then you `import utils.Imports.Bundle._` in every other page/template you make.  
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap5.statik.BootstrapStaticBundle

        object Imports {
          val Bundle = BootstrapStaticBundle()
        }
      """)
    )
  )
}
