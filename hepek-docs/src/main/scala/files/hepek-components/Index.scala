package files.hepek.components

import scalatags.Text.all.*
import utils.Site
import utils.Imports.*

object Index extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek Components")
      .withDescription("Hepek Components")

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek Components",
    frag(
      s"""
          A library for typesafe generating of boilerplate HTML.  

          Mill:
          ```scala
          def ivyDeps = Agg(
            ivy"ba.sake::hepek-components:${Site.hepekVersion}"
          )
          ```

          Sbt:
          ```scala
          libraryDependencies ++= Seq(
            "ba.sake" %% "hepek-components" % "${Site.hepekVersion}"
          )
          ```

          scala-cli:
          ```scala
          //> using dep ba.sake::hepek-components:${Site.hepekVersion}
          ```

          Components are mostly *interface-based* so we can compose them and replace with minimal effort.  
          In a nutshell, this means you can **choose CSS/HTML framework implementation**: Bootstrap5/Bulma etc.   
        """.md
    )
  )
}
