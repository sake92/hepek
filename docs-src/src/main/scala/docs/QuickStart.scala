package docs

import ba.sake.hepek.implicits._
import utils._, Imports._
import templates.HepekDocsPage

object QuickStart extends HepekDocsPage {

  override def pageSettings = PageSettings("Quickstart")

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
      Download the starter project from [here](${links.StarterProjectURL}) and you're ready to roll.  
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
    s"""
      You can just open `index.html` and see the results...  
      If you really need a web server, I'd recommend [Web Server for Chrome](https://chrome.google.com/webstore/detail/web-server-for-chrome/ofhbbkphhbklhfoeikjpcbhemlocgigb).
      Very simple to use, enough for static sites. :)

      Another great way is with [Browsersync](${links.BrowsersyncUrl}).
      Install it, `cd` to rendering folder and type:  
      `browser-sync start --server --files .`  
      Browsersync will **automatically refresh page** in browser when it gets changed, or its CSS etc!

      Deployment consists of copying the contents to your web host (e.g. Github pages).
    """.md
  )

}
