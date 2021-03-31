package docs.hepek

import scalatags.Text.all._
import utils.Imports.Bundle._
import utils.Imports._
import utils._

object QuickStart extends HepekDocsPage {

  override def pageSettings: PageSettings =
    super.pageSettings.withTitle("Quick start")

  override def blogSettings: BlogSettings = super.blogSettings.withSections(
    installationSection,
    usingSection,
    servingSection
  )

  val installationSection: Section = Section(
    "Installation",
    """
      You need to have Java 8 installed and sbt version 1 or greater.  
      It's planned to support other build tools in the future: Mill, Gradle etc.
    """.md
  )

  val usingSection: Section = Section(
    "Using",
    s"""
      Clone the starter project from [here](${links.StarterProjectURL}) and you're ready.  
      Start up sbt console and type `hepek`.  
      Voila, that's it! :)

      You can also make sbt **watch for file changes**.  
      Just type `~hepek` and you'll see changes applied.

      > HTML/CSS or whatever you're rendering will be in `/target/web/public/main/site` folder.  
      > You can make an sbt task for copying these to a more desirable location.  
      > Or you can use `sbt-ghpages` for even faster deployment!
    """.md
  )

  val servingSection: Section = Section(
    "Serving",
    div(
      s"""
        Great tool for developing static sites is [Browsersync](${links.BrowsersyncUrl}).  
        Install it, `cd` to rendered folder and type:  
      """.md,
      chl.bash.withPrompt("$")("browser-sync start --server --files ."),
      """
        Browsersync will **automatically refresh page** in browser when it gets changed!
  
        Deployment consists of copying the contents to your web host (e.g. Github pages).
      """.md
    )
  )
}
