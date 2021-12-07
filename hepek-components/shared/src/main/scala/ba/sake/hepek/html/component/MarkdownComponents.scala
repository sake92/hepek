package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

// TODO support JS ?
trait MarkdownComponents {

  extension (str: String) def md: Frag
}
