package files.hepek

import utils.Imports.Bundle.*, Tags.*

object Index extends HepekSsgDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek SSG")
      .withLabel("Hepek SSG")
      .withDescription("Hepek static site generator")

  val currYear = java.time.LocalDate.now()

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek static site generator",
    frag(
      s"""
        Hepek SSG writes Scala `object`s to files.  
        An `object MyPage` is written to a file `my-page.html`.  
        There is no special new markup/template language to learn, just plain old **Scala**.  
        `Ctrl` + `Space` and you get all the help you need.  
        
        Of course, instead of stitching `String`s together you use:
        - [Scalatags](https://com-lihaoyi.github.io/scalatags/) DSL for HTML
        - Markdown for text
        
        ### Scalatags
        Example:
        ```scala
        def pageContent = div(
          h1("My Page"),
          ul(
            li("hello"),
            li("world")
          )
        )
        ```
        With Scalatags:
        - it is impossible to forget closing tags
        - you can use for loops, collections and all goodies Scala provides
        - reuse is trivial with helper `def`s
        
        
        ### Markdown
        
        Example:
        ```scala
        def pageContent = div(
          s\"\"\"
            ## My Page
            - hello
            - world  
          \"\"\".md
        )
        ```
        This makes it easy to interpolate values inside markdown, rather that using error-prone YAML front matter..
        
        
        ### Static to the max
        
        #### Code snippets
        The code snippets in markdown are rendered statically, via [Shiki](https://shiki.style/) nodejs library.  
        No need for any JS libraries in the browser.  
        > Note that you need to have nodejs installed.
        
        #### Math snippets
        
        
        ### Why Hepek?
        There are no kilometers of HTML, emmet snippets, regex find-and-replace...  
        Everything is nice and tidy with functions, for loops, data abstractions, markdown etc.  

        Content is just a Scala `object`, use it directly
          to construct table of contents, RSS feed, `sitemap.xml`, PDFs etc.  

        Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
        Me too, the year is ${currYear.getYear}, we can do better!  
        Just use `.ref` method and you're done!  
        Hepek SSG **figures out relative path for you**!
      """.md
    )
  )
}
