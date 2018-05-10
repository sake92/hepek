package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import ba.sake.hepek.html.component.GridComponents._
import hepek.templates._
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._

object Docs extends HepekDocsStaticPage {

  override def pageTitle = "Docs"

  override def pageContent =
    row(
      third1(),
      third2(
        div(cls := "pages-toc")(margin := 21.px)(
          SiteMapHTML.siteMap(this)
        )
      ),
      third3()
    )

}
