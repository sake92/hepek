package files.integrations

import scalatags.Text.all.*
import utils.Site
import utils.Imports.*
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
        Hepek supports Play 2.9 and 3.0 with Scala 3.

        Mill:
        ```scala
        def ivyDeps = Agg(
          ivy"ba.sake::hepek-play-3_0:${Site.hepekVersion}"
        )
        ```

        Sbt:
        ```scala
        libraryDependencies ++= Seq(
          "ba.sake" %% "hepek-play-3_0" % "${Site.hepekVersion}"
        )
        ```

        scala-cli:
        ```scala
        //> using dep ba.sake::hepek-play-3_0:${Site.hepekVersion}
        ```

        Examples:
        - [hepek-play-example](https://github.com/sake92/hepek-play-example)
      """.md
    )
  )
}
