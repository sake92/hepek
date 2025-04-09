package files.hepek.tutorials

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object QuickStart extends HepekTutorialPage {

  override def pageSettings =
    super.pageSettings.withTitle("Quick start")

  override def blogSettings = super.blogSettings.withSections(
    gettingStartedSection,
    servingLocallySection
  )

  val gettingStartedSection = Section(
    "Getting started",
    s"""
      You need to:
      - install Java 11+
      - install Sbt or Mill (included in starter template)
      - clone the [starter project](${links.StarterProjectURL})
      
      and you're ready!
    """.md,
    List(
      Section(
        "Mill",
        s"""
          Type `./mill site.hepek`  
          Or `./mill -w site.hepek` so that mill **watches for file changes** and runs `hepek` automatically.  

          Output will be in the `site/hepek_output` folder.
        """.md
      ),
      Section(
        "Sbt",
        s"""
          Start up `sbt` console and type `hepek`.  
          Or `~hepek` so that sbt **watches for file changes** and runs `hepek` automatically.  

          Output will be in the `site/hepek_output` folder.
        """.md
      )
    )
  )

  val servingLocallySection = Section(
    "Serving locally",
    s"""
      Usually you'll want to use an HTTP server to develop a site locally.  
      First `cd site/hepek_output` and then use one of the options below.
    """.md,
    List(
      Section(
        "jwebserver",
        frag(
          s"""
          If using Java 18+, use the `jwebserver` tool:
          ```shell
          $$> jwebserver
          ```
          By default it will serve the site at [http://localhost:8000](http://localhost:8000)
          """.md
        )
      ),
      Section(
        "Browsersync",
        frag(
          s"""
          Install [Browsersync](${links.BrowsersyncUrl}) and then:
          ```shell
          $$> browser-sync
          ```
          Browsersync will **automatically refresh page** in browser when it gets changed!  
          By default it will serve the site at [http://localhost:3000/](http://localhost:3000/)
          """.md
        )
      )
    )
  )

}
