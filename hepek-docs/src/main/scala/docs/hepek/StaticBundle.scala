package docs.hepek

import scalatags.Text.all._
import utils._, Imports._

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

        Recommended usage is to create an `Imports` object which extends one of the predefined bundles.  
        Then you `import utils.Imports._` in every other page/template you make.  
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle

        object Imports extends BootstrapStaticBundle
      """)
    )
  )
}
