package ba.sake.hepek.html.statik

import ba.sake.hepek.html._
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.path.ClassPackageRelativePath

trait StaticPage extends Renderable with ClassPackageRelativePath with HtmlPage {
  import scalatags.Text.all._

  def staticSiteSettings: StaticSiteSettings = StaticSiteSettings()

  def pageCategory: Option[String] = None

  def renderPretty = false
  def renderXhtml  = false

  override def render: String =
    // optionally XHTML-ify and pretty-fly (for a white guy)
    HtmlUtils.process(contents, xhtml = renderXhtml, pretty = renderPretty)
}

final case class StaticSiteSettings(
    indexPage: Option[StaticPage] = None,
    mainPages: List[StaticPage] = List.empty
) {
  def withIndexPage(ip: StaticPage)         = copy(indexPage = Some(ip))
  def withIndexPage(ip: Option[StaticPage]) = copy(indexPage = ip)
  def withMainPages(mps: List[StaticPage])  = copy(mainPages = mps)
  def withMainPages(mps: StaticPage*)       = copy(mainPages = mps.toList)
}
