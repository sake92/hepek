package docs.hepek.components

import scalatags.Text.all._
import utils._, Imports._

object Dependencies extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("JS and CSS dependencies")

  override def blogSettings =
    super.blogSettings.withSections(depsSection)

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

          def components: List[(BaseComponentSettings, ComponentDependencies)] = List.empty
        }
      """),
      s"""
        Some dependencies are fetched from the web, via CDNs.  
        But sometimes you need to inject some inline JS and CSS. 
          Those have corresponding settings also.  
        
        You can override any of these to filter/add/change dependencies. :)  
        E.g. `override def scriptURLs = super.scriptURLs ++ "http://whatever/jquery.min.js"`  
        
        Last method, `def components` is used by framework dependencies.  
        E.g. if you want to include Katex, just extend the `KatexDependencies` trait.  
        In it you can override Katex-specific properties.  
        No need to fiddle with its dependencies and config on your own. :)
      """.md
    )
  )

}
