package files.hepek
package reference

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object StaticBundle extends HepekReferencePage {

  override def pageSettings =
    super.pageSettings.withTitle("Static bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Static bundle",
    frag(
      """
        `StaticBundle` has components needed for working with static HTML.  

        Recommended usage is to create a `Bundle` object from one of the predefined bundles.  
        Then you `import utils.Bundle._` in every other page/template you make.  
        Customize it as needed.
      """.md,
      chl.scala("""
        package utils

        import ba.sake.hepek.bootstrap5.statik.BootstrapStaticBundle

        val Bundle = BootstrapStaticBundle()
      """),
      """
        ---
        The `StaticBundle` contains these utilities:
          - `StaticPage`, base trait you need to extend
          - `Tags`, HTML tags
          - `Classes`, standard utility classes
          - `Grid`, grid support
          - `Navbar`, top navbar support
          - `Panel`, panels/cards support
          - `Form`, form inputs
          - `Image`, images support
      """.md
    )
  )
}
