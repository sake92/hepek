package ba.sake.hepek.html.component

import scalatags.Text.all._

trait LinkComponents {
  def hyperlink(_href: String, _aAttrs: AttrPair*)(content: Frag*): Frag
}
