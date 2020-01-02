package ba.sake.hepek.plain.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.LinkComponents

trait PlainLinkComponents extends LinkComponents {

  override def hyperlink(_href: String, _aAttrs: AttrPair*)(content: Frag*): Frag = {
    val inputAttrsFiltered = _aAttrs.filterNot(_.a.name == "href") // ignore href
    a(href := _href, inputAttrsFiltered)(content)
  }
}
