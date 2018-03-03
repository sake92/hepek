package ba.sake.hepek.html.component

import scalatags.Text.all._
import org.commonmark.node._
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import ba.sake.hepek.utils._

object BasicComponents extends BasicComponents

trait BasicComponents {
  import BasicComponents._

  /** Anchor link : <a href="hreff"> */
  def hyperlink(hreff: String, newWindow: Boolean = false) = {
    val optParams = if (newWindow) List(target := "_blank") else List()
    a(href := hreff, optParams)
  }

  /** Markdown snippet */
  def md(str: String) = {
    val parser   = Parser.builder().build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer.builder().build()
    val result   = renderer.render(document)
    raw(result)
  }

}
