package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import ba.sake.hepek.html.component.GridComponents._
import hepek.templates._
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._

object Index extends HepekDocsStaticPage {

  override def pageTitle       = "Welcome!"
  override def pageDescription = Option("Hepek docs")

  override def screenRatios = super.screenRatios.copy(
    lg = Ratios(Ratio(List(1, 1)), Ratio(List(1, 4, 1)))
  )

  override def pageContent =
    frag(
      div(cls := "page-header text-center")(
        h1("Welcome!")
      ),
      row(
        third1(),
        third2(
          div(cls := "well well-lg")(
            h3("Docs:"),
            div(cls := "pages-toc")(SiteMapHTML.siteMap(this)),
          ),
          md("""
            ## What is hepek?
            It is a tool that turns Scala `object`s into files.  
            Everything that can be represented as a `String` can be written to a file.

            How? By extending `Renderable` trait, directly or via some predefined abstraction.  
            Hepek takes content returned by `render` method and writes it to corresponding file.

            ## What else?
            It contains lots of useful abstractions for *rendering stuff*, like blogging, css layout, code highlighting.  
            There is no special new markup/template language to learn, just plain old **Scala**.  
            `Ctrl` + `Space` and you get all the help needed. :D  
            These abstractions are optional, you can construct your HTML with barebone strings if you want...  
            You can use Scalatags, Markdown or whatever you find useful.
            
            ## Why should you use it?
            Since you type content in Scala, you can use functions, for loops, data abstractions etc.  
            Say goodbye to emmet snippets and regex find-and-replace... :)  
            Functions are handy when you later want to typecheck your code examples or similar stuff.

            Also, static content is represented with Scala's `object`, you can manipulate those directly,
              to construct your RSS feed, sitemap.xml, PDF and Word docs.

            Sick of rewriting relative urls all over the place (`my/folder/../styles.....`)?  
            Just use `relTo` method and you're done! Hepek does that for you.

            Hepek uses *incremental rendering* by default, 
              but you can turn it off if you want (LOL, why would you do that?).  
            If you change an object, it analyses its dependencies and renders objects which depende on it also.

            Linux, OSX, Windows supported? Of course.  
            Please report issues when you find them.

            And, yes, this site is built with hepek. :)

          """)
        ),
        third3()
      ),
      super.pageContent
    )

}
