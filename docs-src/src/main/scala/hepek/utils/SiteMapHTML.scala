package hepek.utils

import scalatags.Text.all._
import ba.sake.hepek.html.structure.StaticPage
import hepek.utils.Imports._

object SiteMapHTML extends SiteMapHTML

trait SiteMapHTML {

  // Site map, HTML for users ("sitemap" is XML, dont be confused :p)
  def siteMap(renderingPage: StaticPage) =
    ul(
      Site.pages.map { mp =>
        li(
          hyperlink(renderingPage.relTo(mp))(mp.pageTitle)
        )
      }
    )

}
