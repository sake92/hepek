package docs

import hepek.templates._
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._
import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section

object Index extends HepekDocsStaticPage {

  override def pageTitle       = "Hepek docs"
  override def pageDescription = Option("Hepek docs")

  override def pageContent =
    frag(
      div(cls := "page-header text-center")(
        h1("Hepek docs")
      ),
      row(
        div(cls := "well well-lg col-md-6 col-md-push-3 ")(
          p("Contents:"),
          div(cls := "pages-toc")(SiteMapHTML.siteMap(this)),
        )
      )
    )

}
