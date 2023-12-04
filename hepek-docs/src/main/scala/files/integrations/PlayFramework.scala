package files.integrations

import utils.Imports._
import utils.Imports.Bundle.*, Tags.*
import utils.Site

object PlayFramework extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Play Framework")
      .withLabel("Play Framework")
      .withDescription("Play Framework - Hepek integrations")

  override def blogSettings =
    super.blogSettings.withSections(pfSection)

  val pfSection = Section(
    "Quick start",
    frag(
      s"""
        Hepek Play Framework integration:
        ```scala
        // mill
        def ivyDeps = Agg(
          ivy"ba.sake::hepek-play:${Site.hepekVersion}"
        )
        
        // sbt
        libraryDependencies ++= Seq(
          "ba.sake" %% "hepek-play" % "${Site.hepekVersion}"
        )
        ```

        See the [example repo](https://github.com/sake92/play-hepek-example)
      """.md
    )
  )
}
