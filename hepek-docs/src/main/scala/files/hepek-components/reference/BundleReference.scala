package files.hepek.components.reference

import scalatags.Text.all.*
import utils.Imports.*

object BundleReference extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings.withTitle("Bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Bundle",
    frag(
      """
        Bundle contains components provided by a HTML/CSS framework.  
        Currently supported frameworks are Bootstrap5, Bootstrap3, Bulma.

        It is recommended to create an `Imports` object which contains one of the predefined bundles:
        ```scala
        package utils

        import ba.sake.hepek.bootstrap5.BootstrapBundle
        
        object Imports:
          val Bundle = BootstrapBundle.default
        ```
      
        Then you `import utils.Imports.Bundle.*` in every other page/template you make.  
        This provides you with flexibility of changing your framework with just one line.

        Every `Bundle` contains utilities like `Page`, `Grid`, `Form`, `Classes`, `Tags` etc.  
        Your code will stay the ~same no matter which framework you use.

        You can customize the `Bundle` and its components with corresponding `with*` methods!
      """.md
    )
  )
}
