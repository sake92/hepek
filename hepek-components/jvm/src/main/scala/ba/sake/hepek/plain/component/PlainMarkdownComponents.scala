package ba.sake.hepek.plain.component

import ba.sake.hepek.scalatags.all._
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import ba.sake.hepek.html.component.MarkdownComponents
import ba.sake.hepek.utils.StringUtils

trait PlainMarkdownComponents extends MarkdownComponents {

  def md(str: String): Frag = {
    val parser   = Parser.builder().build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer.builder().build()
    val result   = renderer.render(document)
    raw(result)
  }
}
