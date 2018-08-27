package docs

import scalatags.Text.all._

object Docs extends templates.HepekDocsStaticPage {

  override def pageTitle = "Docs"

  override def pageContent =
    row(
      third1(),
      third2(
        div(cls := "pages-toc")(margin := 21.px)(
          utils.SiteMapHTML.siteMap(this)
        )
      ),
      third3()
    )

}
