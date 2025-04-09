package files.hepek.components.reference

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object CodeHighlighter extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings.withTitle("Code highlighter")

  override def blogSettings =
    super.blogSettings.withSections(codeHighlighterSection)

  def codeHighlighterSection = Section(
    "Code highlighter",
    frag(
      s"""
        Hepek has support for the awesome PrismJS code highlighter.  
        Add its dependencies by extending `PrismDependencies`.  

        You can use its goodies by importing the `PrismCodeHighlightComponents` trait.  
        It is recommended to make a helper in the `Imports` object so you can tweak it if needed:
        ```scala
        object Imports:
          ...
          val chl = PrismCodeHighlightComponents.default
        ```

        Example:
        ```scala
        chl.java(""\"
          public class HelloWorld {
              public static void main(String[] args) {
                  System.out.println("Hello, Java!"); // a comment
              }
          }
        ""\")
        ```
        
        gets rendered as:
        ```java
        public class HelloWorld {
            public static void main(String[] args) {
                System.out.println("Hello, Java!"); // a comment
            }
        }
        ```
      
        ---
        There is support for all PrismJS languages.  
        Also, most of PrismJS features are expressed as methods:
        - `chl.<language>.ajax("some.url")` fetches file via AJAX
        - `chl.<language>.github("TheAdnan", "focustube", "index.js")` fetches file from Github
        - `chl.<language>.gist("65a82e76597f2fb6c2af", Option("Brick.ts")` fetches from Gist
        - `chl.<language>.withLineNumsStart(-2)` sets line numbers start position (see configuration below also)
        - `chl.<language>.withLineHighlight("1,5-6")` highlights lines 1, 5 and 6
        - `chl.<console-language>.withPrompt("my~awsome~prompt>")` sets prompt
        - `chl.<console-language>.withUser("superadmin", "10.0.0.7")` sets user and host (to be used as prompt)
        - `chl.<console-language>.withOutputLines("2-5")` sets console output lines
        - `chl.markup` highlights HTML

        Note that `withPrompt`, `withUser` and `withOutputLines` 
          are available only on console languages like `bash` and `batch`. Nifty! :)
        
        Examples are available [here](${links.PrismJSExampleUrl}).
      """.md
    )
  )
}
