package files.hepek

import utils.Imports.*

object Index extends HepekSsgDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek SSG")
      .withLabel("Hepek SSG")
      .withDescription("Hepek Static Site Generator")

  override def styleURLs =
    super.styleURLs ++ List("https://cdn.jsdelivr.net/npm/katex@0.16.22/dist/katex.min.css")

  val currYear = java.time.LocalDate.now()

  override def blogSettings = super.blogSettings.withSections(
    introSection,
    staticSection,
    whySection
  )

  val introSection = Section(
    "Hepek Static Site Generator",
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
    """.md
  )

  val staticSection = Section(
    "Static to the Max",
    s"""
    ### Code snippets
    The code snippets in markdown are rendered statically, via [Shiki](https://shiki.style/) nodejs library.  
    No need for any JS libraries in the browser.  
    > Note that you need to have nodejs installed.
    
    <!--- awful, I know. But you cant escape a backtick in raw`` js string easily.
    https://stackoverflow.com/a/62463359/4496364
    -->
    ```markdown
    $${"`"}$${"`"}$${"`"}scala
    val str = "Hello, world!"
    $${"`"}$${"`"}$${"`"}
    ```
    renders as:
    ```scala
      val str = "Hello, world!"
    ```
    
    ### Math snippets
    
    ```markdown
    $${"`"}$${"`"}$${"`"}math
    A \\setminus B = {x | x \\in A \\land x \\notin B}
    $${"`"}$${"`"}$${"`"}
    ```
    
    renders as:
    ```math
    
    A \\setminus B = {x | x \\in A \\land x \\notin B}
    
    ```
    
    > Note that you need to have nodejs installed.
    > Note that you need to add the KaTeX CSS!
    """.md
  )

  val whySection = Section(
    "Why Hepek?",
    s"""
    There is no kilometer of HTML, emmet snippets, regex find-and-replace...  
    Everything is nice and tidy with functions, for loops, data abstractions, markdown etc.  

    Content is just a Scala `object`, use it directly
      to construct table of contents, RSS feed, `sitemap.xml`, PDFs etc.  

    Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
    Me too, the year is ${currYear.getYear}, we can do better!  
    Just use `.ref` method and you're done!  
    Hepek SSG **figures out relative path for you**!
    """.md
  )
}
