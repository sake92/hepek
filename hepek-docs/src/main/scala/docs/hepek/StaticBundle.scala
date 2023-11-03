package docs.hepek

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
        `StaticBundle` extends the general `Bundle` with static HTML components.  
        It adds traits like `StaticPage` in addition to existing stuff in `Bundle`.

        Recommended usage is to create an `Imports` object which contains one of the predefined bundles.  
        Then you `import utils.Imports.Bundle._` in every other page/template you make.  
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle

        object Imports {
          val Bundle = BootstrapStaticBundle()
        }
      """)
    )
  )
}
