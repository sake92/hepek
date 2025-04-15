package files.hepek
package tutorials

import scalatags.Text.all.*
import utils.Imports.*
object Index extends HepekTutorialPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek SSG tutorials")
      .withLabel("Tutorials")
      .withDescription("Hepek SSG tutorials")

  override def blogSettings = super.blogSettings.withSections(
    Section(
      "Tutorials",
      frag(
        s"""
          Tutorials to get you started quickly with Hepek.



        """.md
        // TODO dodat next / prev
      )
    )
  )

}
