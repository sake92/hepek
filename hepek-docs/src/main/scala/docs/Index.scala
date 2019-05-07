package docs

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils.Imports._
import templates.HepekDocsStaticPage
import java.time.LocalDate

object grid extends Grid {
  override def screenRatios =
    super.screenRatios
      .withSm(None)
      .withXs(None)
      .withLg(Ratios(Ratio(1, 1), Ratio(1, 4, 1)))
      .withMd(Ratios(Ratio(1, 1), Ratio(1, 4, 1)))
}

object Index extends HepekDocsStaticPage {
  import grid._

  override def pageSettings =
    super.pageSettings
      .withTitle("Welcome!")
      .withDescription("Hepek docs")

  val currYear = LocalDate.now()

  override def pageContent =
    frag(
      div(cls := "page-header text-center")(
        h1("Welcome!")
      ),
      row(
        third(),
        third(
          s"""
            Hepek turns Scala `object`s into files.
            Everything you can `println` to screen *Hepek* can write to a file.

            It contains lots of useful functions for *rendering stuff*, 
              like blogging support, css layout, code highlighting.  
            There is no special new markup/template language to learn, just plain old **Scala**.  
            `Ctrl` + `Space` and you get all the help needed.
            You can use Scalatags, Markdown or whatever syntax **you** find useful.
            
            ## So what?
            Say goodbye to emmet snippets and regex find-and-replace...
            Now you can use functions, for loops, data abstractions etc. What more do you need really?  

            Also, since content is just a Scala `object`, you can use it directly
              to construct table of contents, RSS feed, `sitemap.xml`, PDF and Word docs.  

            Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
            Me too, the year is ${currYear.getYear()}, we can do better!  
            Just use `.ref` method and you're done! Hepek figures out path instead of you.

            Hepek uses *incremental rendering* by default.  
            If you change an object, it only renders objects which depend on it. Neat!

            Linux, OSX, Windows supported? Of course. Thanks JVM!  

            And, yes, this site is built with Hepek.

          """.md
        ),
        third()
      ),
      super.pageContent
    )

}
