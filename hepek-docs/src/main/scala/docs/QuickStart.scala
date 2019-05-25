package docs

import scalatags.Text.all._
import utils._, Imports._

object QuickStart extends templates.HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Docs")

  override def blogSettings = super.blogSettings.withSections(
    installationSection,
    usingSection,
    servingSection
  )

  /* CONTENT */
  val installationSection = Section(
    "Installation",
    """
      You need to have Java 8 installed and sbt version 1 or greater.  
      It's planned to support other build tools in the future: Mill, Gradle etc.
    """.md
  )

  val usingSection = Section(
    "Using",
    s"""
      Just type `sbt new sake92/hepek-starter.g8` to generate a project.  
      Or you can the starter project from [here](${links.StarterProjectURL}).  

      Start up sbt console and type `hepek`.  
      Voila, that's it! :)

      You can also make sbt **watch for file changes**.
      Just type `~hepek` and you'll see changes in matter of a second.

      > HTML/CSS or whatever you're rendering will be in `/target/web/public/main/site` folder.  
      > You can make an sbt task for copying these to a more desirable location.  
    """.md
  )

  val servingSection = Section(
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
