package ba.sake.hepek.html.component

import scalatags.Text.all._
import org.commonmark.node._
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.utils._

object BasicComponents extends BasicComponents

trait BasicComponents extends LinkComponents with CommonmarkComponents

/** Mixin for additional Renderable goodies. */
trait BasicRenderableComponents extends BasicComponents { self: Renderable =>
  import BasicComponents._

  def hyperlink(other: Renderable, _aAttrs: AttrPair*)(content: Frag*): Frag =
    hyperlink(relTo(other), _aAttrs: _*)(content: _*)
}

trait LinkComponents {

  def hyperlink(hreff: String, _aAttrs: AttrPair*)(content: Frag*): Frag = {
    val inputAttrsFiltered = _aAttrs.filterNot(_.a.name == "href") // ignore href
    a(href := hreff, inputAttrsFiltered)(content)
  }
}

trait CommonmarkComponents {

  /** Markdown snippet */
  def md(str: String) = {
    val parser   = Parser.builder().build()
    val document = parser.parse(StringUtils.unindent(str))
    val renderer = HtmlRenderer.builder().build()
    val result   = renderer.render(document)
    raw(result)
  }
}
