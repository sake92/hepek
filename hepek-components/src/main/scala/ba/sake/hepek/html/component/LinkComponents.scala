package ba.sake.hepek.html.component

import scalatags.Text.all._

trait LinkComponents {

  def hyperlink(_href: String, _aAttrs: AttrPair*)(content: Frag*): Frag = {
    val inputAttrsFiltered = _aAttrs.filterNot(_.a.name == "href") // ignore href
    a(href := _href, inputAttrsFiltered)(content)
  }
}
