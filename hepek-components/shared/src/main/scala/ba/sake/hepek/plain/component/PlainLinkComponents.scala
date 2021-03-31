package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.LinkComponents
import ba.sake.hepek.scalatags.all._

trait PlainLinkComponents extends LinkComponents {

  override def hyperlink(_href: String, _aAttrs: AttrPair*)(content: Frag*): Frag = {
    val inputAttrsFiltered = _aAttrs.filterNot(_.a.name == "href") // ignore href
    a(href := _href, inputAttrsFiltered)(content)
  }
}
