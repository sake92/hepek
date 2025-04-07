package ba.sake.hepek.bulma.markdown

import java.util as ju
import scala.jdk.CollectionConverters.*
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.html.{AttributeProvider, HtmlRenderer}
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension
import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.ext.attributes.internal.AttributesAttributeProvider
import com.vladsch.flexmark.html.renderer.{AttributablePart, LinkResolverContext}
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.html.MutableAttributes
import ba.sake.hepek.utils.StringUtils
import ba.sake.hepek.markdown.MarkdownHandler
import ba.sake.hepek.scalatags.all.*

class BulmaMarkdownHandler extends MarkdownHandler {
  override def render(str: String, suppressHTML: Boolean, escapeHTML: Boolean): Frag = {
    val options = new MutableDataSet()
    options.set(
      Parser.EXTENSIONS,
      List(
        TablesExtension.create(),
        StrikethroughExtension.create()
      ).asJava: ju.Collection[Extension]
    )
    options.set(HtmlRenderer.ESCAPE_HTML, escapeHTML)
    options.set(HtmlRenderer.SUPPRESS_HTML, suppressHTML)

    val parser   = Parser.builder(options).build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer
      .builder(options)
      .attributeProviderFactory(new AttributesAttributeProvider.Factory() {
        override def apply(context: LinkResolverContext): AttributeProvider =
          new BulmaAttributeProvider(context)
      })
      .build()
    val result = renderer.render(document)
    raw(result)
  }
}

private class BulmaAttributeProvider(context: LinkResolverContext)
    extends AttributesAttributeProvider(context) {

  override def setAttributes(
      node: Node,
      part: AttributablePart,
      attributes: MutableAttributes
  ): Unit =
    super.setAttributes(node, part, attributes)
    node match {
      case h: Heading =>
        attributes.addValue("class", s"title is-${h.getLevel()}")
      case _ =>
    }
}
