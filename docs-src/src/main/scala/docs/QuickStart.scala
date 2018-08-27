package docs

import ba.sake.hepek.implicits._
import utils._, Imports._
import templates.HepekDocsPage

object QuickStart extends HepekDocsPage {

  override def pageTitle = "Quickstart"

  override def postSections = List(
    installationSection,
    usingSection,
    servingSection
  )

  /* CONTENT */
  val installationSection = Section(
    "Installation",
    """
      Prerequisites:
        - Java 8
        - sbt

      It's planned to support other build tools in the future: Mill, Gradle etc.  
    """.md
  )

  val usingSection = Section(
    "Using",
    s"""
      Download the starter project from [here](${links.StarterProjectURL}) and you're ready to roll.  
      Start up sbt console and type `hepek`.  
      Voila, that's it! :)

      You can also make sbt **watch** for files changes.  
      Just type `~hepek` and you'll see changes in matter of a second.

      > HTML/CSS or whatever you're rendering will be in `/target/web/public/main/site` folder.  
      > You can make an sbt task for copying these to a more desirable location.  
    """.md
  )

  val servingSection = Section(
    "Serving",
    md(s"""
      You can just open `index.html` and see the results...  
      If you really need a web server, I'd recommend [Web Server for Chrome](https://chrome.google.com/webstore/detail/web-server-for-chrome/ofhbbkphhbklhfoeikjpcbhemlocgigb).
      Very simple to use, enough for static sites. :)  
      Deployment consists of copying the contents to your web host (e.g. Github pages).
    """)
  )

}
