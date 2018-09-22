package utils

import ba.sake.hepek.core.RelativePath
import scalatags.Text.all._
import utils.Imports._

object SiteMapHTML extends SiteMapHTML

trait SiteMapHTML {

  // Site map, HTML for users ("sitemap" is XML, dont be confused :p)
  def siteMap(implicit renderingPage: RelativePath) =
    ul(cls := "list-group")(
      Site.pages.map { mp =>
        li(cls := "list-group-item")(
          hyperlink(renderingPage.relTo(mp))(mp.pageSettings.title)
        )
      }
    )

}
