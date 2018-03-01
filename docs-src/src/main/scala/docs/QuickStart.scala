package docs

import hepek.templates.HepekDocsPage
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._
import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section

object QuickStart extends HepekDocsPage {

  override def pageTitle       = "QuickStart"
  override def pageDescription = Option("Hepek quickstart")

  override def postSections = List(
    Section("Installation", installationSectionContent),
    Section("Using", usingSectionContent)
  )

  /* CONSTS */
  val starterProjectURL = "https://github.com/sake92/hepek-examples"

  /* CONTENT */
  val installationSectionContent =
    frag(
      md("""
          Prerequisites:
            - Java 8 JDK (because Scala 2.12 uses it)
            - sbt

          There is a plan to support other build tools in the future: CBT, Mill, Gradle etc.  
          Contributions are welcome.

          So, there is no classic installation.
        """)
    )

  val usingSectionContent =
    frag(
      md(s"""
            Download the starter project from [here]($starterProjectURL) and you're ready to roll.  
            Start up sbt console and type `hepek`.  
            Voila, that's it! :)

            > HTML/CSS or whatever you're rendering will be in `/target/web/public/main/site` folder.
            > You can make an sbt task for copying these to a more desirable location.
          """)
    )

}
