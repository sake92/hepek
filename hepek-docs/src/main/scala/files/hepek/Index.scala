package files.hepek

import utils.Imports.Bundle.*, Tags.*

object Index extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Hepek SSG")
      .withDescription("Hepek SSG")

  val currYear = java.time.LocalDate.now()

  override def blogSettings = super.blogSettings.withSections(
    introSection
  )

  val introSection = Section(
    "Hepek, static site generator",
    frag(
      s"""
        Hepek SSG turns Scala `object`s into files.  
        Everything you can `println`, *Hepek* SSG can write to a file.  
        `object MyPage ..` == `my-page.html`

        It contains lots of utilities for *rendering stuff*, 
          like blog support, themes, PDF rendering etc.  
        There is no special new markup/template language to learn, just plain old **Scala**.  
        `Ctrl` + `Space` and you get all the help needed.
        You can use Scalatags, Markdown or **whatever syntax you prefer**.
        
        ---
        You can ask... so what?  
        This is yet another SSG, what makes it so special?  
        Forget about emmet snippets, kilometers of HTML, regex find-and-replace...
        Now you can use functions, for loops, data abstractions etc.  
        What more do you need really?  

        Content is just a Scala `object`, you can use it directly
          to construct table of contents, RSS feed, `sitemap.xml`, PDFs etc.  

        Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
        Me too, the year is ${currYear.getYear()}, we can do better!  
        Just use `.ref` method and you're done!  
        Hepek SSG **figures out relative path for you**!
      """.md
    )
  )
}
