package docs

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils._, Imports._
import templates.HepekDocsPage

object Dependencies extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("JS and CSS dependencies")

  override def postSections = List(
    depsSection
  )

  /* CONTENT */
  val depsSection = Section(
    "JS/CSS dependencies",
    frag(
      s"""
        Every framework has its dependencies, JS and/or CSS.  
        These are expressed with [`PageDependencies`](${links.PageDependenciesUrl}) trait:
      """.md,
      chl.scala("""
        trait PageDependencies {
          def styleURLs: List[String]    = List.empty
          def stylesInline: List[String] = List.empty

          def scriptURLs: List[String]    = List.empty
          def scriptsInline: List[String] = List.empty
        }
      """),
      s"""
        Some dependencies are fetched from the web, via CDNs.  
        But sometimes you need to inject some inline JS and CSS. 
          Those have corresponding settings also.  
        
        When you extend `SomeLibraryDependencies`, 
          these library dependencies are added to the final list of dependencies.  
        E.g. `override def scriptURLs = super.scriptURLs ++ "http://whatever/jquery.min.js"`  
        These `scriptURLs` are appended as scripts at the end of body.  
        Then the `scriptsInline` are added after those.

        ---
        Of course, you can also override these to filter/add/change dependencies. :)
      """.md
    )
  )

}
