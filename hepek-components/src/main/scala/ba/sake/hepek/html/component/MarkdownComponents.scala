package ba.sake.hepek.html.component

import scalatags.Text.all._
import ba.sake.hepek.utils.StringUtils
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

trait MarkdownComponents { self =>
  def md(str: String): Frag

  implicit class MarkdownComponentOps(private val str: String) {
    def md: Frag = self.md(str)
  }
}

trait CommonmarkComponents extends MarkdownComponents {

  def md(str: String): Frag = {
    val parser   = Parser.builder().build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer.builder().build()
    val result   = renderer.render(document)
    raw(result)
  }
}
