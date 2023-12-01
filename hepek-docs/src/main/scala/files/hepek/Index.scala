package files.hepek

import utils.Imports.Bundle.*, Tags.*

object Index extends HepekDocsPage {

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
        Writes Scala `object`s to files.  
        Think about `object MyPage` as a file `my-page.html`.

        Hepek contains lots of utilities for *rendering stuff*: 
          blog support, themes, PDF rendering etc.  
        There is no special new markup/template language to learn, just plain old **Scala**.  
        `Ctrl` + `Space` and you get all the help you need.  
        Use Scalatags, Markdown or **whatever syntax you prefer**.
        
        ---
        How is Hepek different from other SSGs?  
        There are no kilometers of HTML, emmet snippets, regex find-and-replace...  
        Everything is nice and tidy with functions, for loops, data abstractions, markdown etc.  

        Content is just a Scala `object`, use it directly
          to construct table of contents, RSS feed, `sitemap.xml`, PDFs etc.  

        Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
        Me too, the year is ${currYear.getYear()}, we can do better!  
        Just use `.ref` method and you're done!  
        Hepek SSG **figures out relative path for you**!
      """.md
    )
  )
}
