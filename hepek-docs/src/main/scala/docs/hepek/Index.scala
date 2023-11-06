package docs.hepek

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

        It contains lots of useful functions for *rendering stuff*, 
          like blogging support, themes, PDF rendering etc.  
        There is no special new markup/template language to learn, just plain old **Scala**.  
        `Ctrl` + `Space` and you get all the help needed.
        You can use Scalatags, Markdown or **whatever syntax you prefer**.
        
        ---
        You can ask maybe... so what?  
        This is yet another SSG, what makes it so special?  
        Well, say goodbye to emmet snippets and regex find-and-replace...
        Now you can use functions, for loops, data abstractions etc. What more do you need really?  

        Also, since content is just a Scala `object`, you can use it directly
          to construct table of contents, RSS feed, `sitemap.xml`, PDFs and Word docs.  

        Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
        Me too, the year is ${currYear.getYear()}, we can do better!  
        Just use `.ref` method and you're done! Hepek SSG **figures out relative path for you**!

        Hepek SSG uses **incremental rendering** by default.  
        If you change an object, it only renders it and objects which depend on it. Neat!

        Linux, OSX, Windows supported? Of course. Thanks JVM!  

        And, yes, this site is built with Hepek SSG.

      """.md
    )
  )
}
