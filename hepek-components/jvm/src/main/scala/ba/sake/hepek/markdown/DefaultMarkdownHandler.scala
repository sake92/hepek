package ba.sake.hepek.markdown

import java.util.Collection
import scala.jdk.CollectionConverters.*
import scalatags.Text.all.*
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension
import ba.sake.hepek.utils.StringUtils

class DefaultMarkdownHandler extends MarkdownHandler {

  def render(str: String, suppressHTML: Boolean, escapeHTML: Boolean = false): Frag = {
    val options = new MutableDataSet()
    options.set(
      Parser.EXTENSIONS,
      List(
        TablesExtension.create(),
        StrikethroughExtension.create()
      ).asJava: Collection[Extension]
    )
    options.set(HtmlRenderer.ESCAPE_HTML, escapeHTML)
    options.set(HtmlRenderer.SUPPRESS_HTML, suppressHTML)

    val parser   = Parser.builder(options).build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer.builder(options).build()
    val result   = renderer.render(document)
    raw(result)
  }
}

object DefaultMarkdownHandler {
  def apply(): MarkdownHandler = new DefaultMarkdownHandler()
}
