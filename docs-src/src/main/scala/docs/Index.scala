package docs

import hepek.templates._
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._
import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import ba.sake.hepek.html.component.GridComponents._

object Index extends HepekDocsStaticPage {

  override def pageTitle       = "Welcome!"
  override def pageDescription = Option("Hepek docs")

  override def lgRatios = Ratios(Ratio(List(1, 1)), Ratio(List(1, 4, 1)))

  override def pageContent =
    frag(
      div(cls := "page-header text-center")(
        h1("Welcome!")
      ),
      row(
        third1(),
        third2(
          div(cls := "well well-lg")(
            p("Dig into docs:"),
            div(cls := "pages-toc")(SiteMapHTML.siteMap(this)),
          ),
          md("""
            **Hepek** is just a library with useful abstractions for *rendering stuff*.  
            There is no special new markup/template language to learn, just plain old **Scala**.  
            Everything else is optional, you can construct your HTML with barebone strings if you want.  
            You can use Scalatags, Markdown or whatever you find useful.
            
            Since you type content in Scala, you can use functions, for loops, data abstractions etc.  
            Say goodbye to emmet snippets and regex find-and-replace... :)  
            Functions are handy when you later want to typecheck your code examples or similar stuff.

            Also, since static content is represented with Scala's `object` you can manipulate those immediately,
              to construct your RSS feed, sitemap.xml, PDF and Word docs.

            Sick of rewriting relative urls all over the place (`my/folder/../styles.....`)?  
            Just use `relTo` method and you're done! Hepek does that for you.


          """)
        ),
        third3()
      ),
    )

}
