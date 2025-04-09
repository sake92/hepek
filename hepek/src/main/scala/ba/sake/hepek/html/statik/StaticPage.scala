package ba.sake.hepek.html.statik

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html.*
import ba.sake.hepek.markdown.*
import ba.sake.hepek.path.ClassPackageRelativePath

trait StaticPage extends Renderable with ClassPackageRelativePath with HtmlPage {
  
  def shikiSettings: ShikiSettings = ShikiSettings.default

  override def markdownHandler: MarkdownHandler = NodejsShikiMarkdownHandler(shikiSettings.theme)

  def staticSiteSettings: StaticSiteSettings = StaticSiteSettings.default

  def pageCategory: Option[String] = None

  def renderPretty: Boolean = false

  def renderXhtml: Boolean = false

  override def render: String =
    // optionally XHTML-ify and pretty-fly (for a white guy)
    val htmlText = "<!DOCTYPE html>" + contents
    HtmlUtils.process(htmlText, xhtml = renderXhtml, pretty = renderPretty)
}
