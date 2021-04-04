package ba.sake.hepek.html.statik

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html._
import ba.sake.hepek.path.ClassPackageRelativePath
import ba.sake.kalem.Wither

trait StaticPage extends Renderable with ClassPackageRelativePath with HtmlPage {
  def staticSiteSettings: StaticSiteSettings = StaticSiteSettings()

  def pageCategory: Option[String] = None

  def renderPretty: Boolean = false
  def renderXhtml: Boolean  = false

  override def render: String =
    // optionally XHTML-ify and pretty-fly (for a white guy)
    HtmlUtils.process(contents, xhtml = renderXhtml, pretty = renderPretty)
}

@Wither
final case class StaticSiteSettings(
    indexPage: Option[StaticPage] = None,
    mainPages: List[StaticPage] = List.empty
) {

  def withIndexPage(indexPage: Option[StaticPage]): StaticSiteSettings =
    new StaticSiteSettings(indexPage = indexPage, mainPages = mainPages)

  def withIndexPage(indexPage: StaticPage): StaticSiteSettings =
    new StaticSiteSettings(mainPages = mainPages, indexPage = Option(indexPage))

  def withMainPages(mainPages: List[StaticPage]): StaticSiteSettings =
    new StaticSiteSettings(indexPage = indexPage, mainPages = mainPages)

  def withMainPages(mainPages: StaticPage*): StaticSiteSettings =
    new StaticSiteSettings(indexPage = indexPage, mainPages = mainPages.toList)
}
