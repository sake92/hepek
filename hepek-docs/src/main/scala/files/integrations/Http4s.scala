package files.integrations

import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*
import utils.Site

object Http4s extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Http4s")
      .withLabel("Http4s")
      .withDescription("Http4s - Hepek integrations")

  override def blogSettings =
    super.blogSettings.withSections(http4sSection)

  val http4sSection = Section(
    "Quick start",
    frag(
      s"""
        Hepek Http4s integration:
        ```scala
        // mill
        def ivyDeps = Agg(
          ivy"ba.sake::hepek-http4s:${Site.hepekVersion}"
        )
        
        // sbt
        libraryDependencies ++= Seq(
          "ba.sake" %% "hepek-http4s" % "${Site.hepekVersion}"
        )
        ```

        Examples:
        - [hepek-http4s-example](https://github.com/sake92/hepek-http4s-example)
      """.md
    )
  )
}
