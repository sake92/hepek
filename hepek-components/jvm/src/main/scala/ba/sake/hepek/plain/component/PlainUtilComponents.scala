package ba.sake.hepek.plain.component

import java.util.Collection
import scala.jdk.CollectionConverters.*

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet
import com.vladsch.flexmark.util.misc.Extension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.utils.StringUtils

import ba.sake.hepek.html.component.UtilComponents

trait PlainUtilComponents extends UtilComponents {

  extension (str: String)
    def md: Frag = {
      val options = new MutableDataSet()
      options
        .set(
          Parser.EXTENSIONS,
          List(
            TablesExtension.create(),
            StrikethroughExtension.create()
          ).asJava: Collection[Extension]
        )
        .toImmutable()

      val parser   = Parser.builder(options).build()
      val document = parser.parse(StringUtils.unindent(str))
      val renderer = HtmlRenderer.builder(options).build()
      val result   = renderer.render(document)
      raw(result)
    }
}
