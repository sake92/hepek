package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all._

trait LinkComponents {
  def hyperlink(_href: String, _aAttrs: AttrPair*)(content: Frag*): Frag
}
