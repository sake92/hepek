package docs.hepek.play

import scalatags.Text.all._
import utils._, Imports._

object PlayBundle extends HepekPlayDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Play bundle")

  override def blogSettings =
    super.blogSettings.withSections(bundleSection)

  val bundleSection = Section(
    "Play bundle",
    frag(
      """
      `PlayBundle` extends the general `Bundle` with Play HTML components.  
      Recommended usage is to create an `Imports` object which extends one of the predefined bundles.  
      Then you `import utils.Imports._` in every other page/template you make.  
      """.md,
      chl.scala("""
      package utils

      import ba.sake.hepek.play.PlayBundle
      import ba.sake.hepek.bootstrap3.BootstrapBundle

      object Imports extends PlayBundle with BootstrapBundle
      """),
      """
      ---
      `PlayBundle` adds a new helper called  `hf`, short for "hepek form".  
      You can override the `hf` and use a custom form implementation:
      """.md,
      chl.scala("""
      object Imports extends PlayBundle with BootstrapBundle {

        object customForm extends Form { // customize your form components :)
          override def formType = Form.Type.Horizontal()
        }
      
        override val hf = new HepekPlayForm {
          override type FormImpl = Form
          val fc = customForm
        }
      }
      """),
      """
      You can access the underlying form implementation through `hf.fc` field (fc is short for form components).
      """.md
    )
  )
}
