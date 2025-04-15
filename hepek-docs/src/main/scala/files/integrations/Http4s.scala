package files.integrations

import scalatags.Text.all.*
import utils.Site
import utils.Imports.*

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
        Mill:
        ```scala
        def ivyDeps = Agg(
          ivy"ba.sake::hepek-http4s:${Site.hepekVersion}"
        )
        ```

        Sbt:
        ```scala
        libraryDependencies ++= Seq(
          "ba.sake" %% "hepek-http4s" % "${Site.hepekVersion}"
        )
        ```

        scala-cli:
        ```scala
        //> using dep ba.sake::hepek-http4s:${Site.hepekVersion}
        ```

        Examples:
        - [hepek-http4s-example](https://github.com/sake92/hepek-http4s-example)
      """.md
    )
  )
}
