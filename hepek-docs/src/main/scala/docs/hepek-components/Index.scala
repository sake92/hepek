package docs.hepek.components

import utils.Imports.Bundle.*, Tags.*

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
          Hepek Components contain the core components of Hepek SSG and can be used standalone:
          ```scala
          // sbt
          libraryDependencies ++= Seq(
            "ba.sake" %% "hepek-components" % "0.14.0"
          )

          // Mill
          def ivyDeps = Agg(
            ivy"ba.sake::hepek-components:0.14.0"
          )

          // scala-cli
          //> using dep ba.sake::hepek-components:0.14.0
          ```

          Components are mostly *interface-based* so we can compose them easily and replace with minimal effort.  
          In a nutshell, this means you can **choose CSS/HTML framework implementation**: Bootstrap5/Bootstrap3/Bulma.  

          In order to group components, Hepek has a concept of `Bundle`.  
          Every framework fills it with its own implementations of components.  
          Proceed to [next page](${BundleDocs.ref}) for more detailed explanation.
  
        """.md
    )
  )
}
