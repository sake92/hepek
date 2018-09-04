package docs

import ba.sake.hepek.implicits.PageSettings
import scalatags.Text.all._
import utils.Imports.grid._

object Docs extends templates.HepekDocsStaticPage {

  override def pageSettings = PageSettings("Docs")

  override def pageContent =
    row(
      third1(),
      third2(
        div(cls := "pages-toc", margin := 21.px)(
          utils.SiteMapHTML.siteMap
        )
      ),
      third3()
    )

}
