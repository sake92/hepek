package ba.sake.hepek.html.statik

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html._
import ba.sake.hepek.path.ClassPackageRelativePath

trait StaticPage extends Renderable with ClassPackageRelativePath with HtmlPage {

  def staticSiteSettings: StaticSiteSettings = StaticSiteSettings()

  def pageCategory: Option[String] = None

  def renderPretty: Boolean = false

  def renderXhtml: Boolean = false

  override def render: String =
    // optionally XHTML-ify and pretty-fly (for a white guy)
    val htmlText = "<!DOCTYPE html>" + contents
    HtmlUtils.process(htmlText, xhtml = renderXhtml, pretty = renderPretty)
}

final case class StaticSiteSettings(
    indexPage: Option[StaticPage] = None,
    mainPages: List[StaticPage] = List.empty
) {

  def withIndexPage(indexPage: Option[StaticPage]): StaticSiteSettings =
    copy(indexPage = indexPage)

  def withIndexPage(indexPage: StaticPage): StaticSiteSettings =
    copy(indexPage = Option(indexPage))

  def withMainPages(mainPages: List[StaticPage]): StaticSiteSettings =
    copy(mainPages = mainPages)

  def withMainPages(mainPages: StaticPage*): StaticSiteSettings =
    copy(mainPages = mainPages.toList)
}
