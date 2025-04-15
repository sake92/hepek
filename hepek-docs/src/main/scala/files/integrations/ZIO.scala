package files.integrations

import scalatags.Text.all.*
import utils.Site
import utils.Imports.*
object ZIO extends HepekIntegrationsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("ZIO")
      .withLabel("ZIO")
      .withDescription("ZIO - Hepek integrations")

  override def blogSettings =
    super.blogSettings.withSections(zioSection)

  val zioSection = Section(
    "Quick start",
    frag(
      s"""
        Mill:
        ```scala
        def ivyDeps = Agg(
          ivy"ba.sake::hepek-zio:${Site.hepekVersion}"
        )
        ```

        Sbt:
        ```scala
        libraryDependencies ++= Seq(
          "ba.sake" %% "hepek-zio" % "${Site.hepekVersion}"
        )
        ```

        scala-cli:
        ```scala
        //> using dep ba.sake::hepek-zio:${Site.hepekVersion}
        ```

        Examples:
        - [hepek-zio-example](https://github.com/sake92/hepek-zio-example)
      """.md
    )
  )
}
