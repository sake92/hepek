package docs

import ba.sake.hepek.bootstrap3.component.BootstrapGridComponents
import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils.Imports._
import templates.HepekDocsStaticPage

object grid extends BootstrapGridComponents {
  override def screenRatios =
    super.screenRatios
      .withSm(None)
      .withXs(None)
      .withLg(Ratios(Ratio(1, 1), Ratio(1, 4, 1)))
}

object Index extends HepekDocsStaticPage {
  import grid._

  override def pageSettings =
    super.pageSettings
      .withTitle("Welcome!")
      .withDescription("Hepek docs")

  override def pageContent =
    frag(
      div(cls := "page-header text-center")(
        h1("Welcome!")
      ),
      row(
        third1(),
        third2(
          """
            ## What hepek does?
            It turns Scala `object`s into files.  
            Everything you can `println` to screen hepek can write to a file. :)

            ## What else?
            It contains lots of useful functions for *rendering stuff*, 
              like blogging support, css layout, code highlighting.  
            There is no special new markup/template language to learn, just plain old **Scala**.  
            `Ctrl` + `Space` and you get all the help needed. :D  
            You can use Scalatags, Markdown or whatever syntax **you** find useful.
            
            ## So what?
            Say goodbye to emmet snippets and regex find-and-replace... :)  
            Now you can use functions, for loops, data abstractions etc. What more do you need really?  

            Also, since content is just a Scala `object`, you can use it directly
              to construct table of contents, RSS feed, `sitemap.xml`, PDF and Word docs.  

            Sick of rewriting relative urls all over the place, like `my/folder/../styles.....`?  
            Me too, the year is 2018, we can do better!  
            Just use `.ref` method and you're done! Hepek figures out path instead of you. ;)

            Hepek uses *incremental rendering* by default.  
            If you change an object, it renders objects which depend on it also. Neat!

            Linux, OSX, Windows supported? Of course. Thanks JVM!  

            And, yes, this site is built with hepek. :)

          """.md
        ),
        third3()
      ),
      super.pageContent
    )

}
