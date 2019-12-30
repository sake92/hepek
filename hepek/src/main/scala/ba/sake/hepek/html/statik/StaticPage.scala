package ba.sake.hepek.html.statik

import ba.sake.hepek.html._
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.path.ClassPackageRelativePath
import ba.sake.stone.Wither

trait StaticPage extends Renderable with ClassPackageRelativePath with HtmlPage {
  import scalatags.Text.all._

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
)
