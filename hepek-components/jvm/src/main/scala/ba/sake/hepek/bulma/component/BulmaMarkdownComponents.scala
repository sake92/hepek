package ba.sake.hepek.bulma.component

import ba.sake.hepek.scalatags.all._
import org.commonmark.parser.Parser
import org.commonmark.renderer.html._
import ba.sake.hepek.html.component.MarkdownComponents
import ba.sake.hepek.utils.StringUtils
import java.{util => ju}
import org.commonmark.node.Node
import org.commonmark.node.Heading

/* Bulma is a special snowflake, requiring classes for headers... */
trait BulmaMarkdownComponents extends MarkdownComponents {

  def md(str: String): Frag = {
    val parser   = Parser.builder().build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer
      .builder()
      .attributeProviderFactory(new AttributeProviderFactory() {
        def create(context: AttributeProviderContext): AttributeProvider =
          new BulmaAttributeProvider()
      })
      .build()
    val result = renderer.render(document)
    raw(result)
  }
}

private class BulmaAttributeProvider extends AttributeProvider {

  override def setAttributes(
      node: Node,
      tagName: String,
      attributes: ju.Map[String, String]
  ): Unit =
    node match {
      case h: Heading =>
        attributes.put("class", s"title is-${h.getLevel()}")
      case _ =>
    }
}
