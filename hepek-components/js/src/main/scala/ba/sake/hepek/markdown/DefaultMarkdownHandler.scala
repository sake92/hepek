package ba.sake.hepek.markdown

import ba.sake.hepek.scalatags.all.*

class DefaultMarkdownHandler extends MarkdownHandler {
  override def render(str: String, suppressHTML: Boolean, escapeHTML: Boolean): Frag = div(
    "TODO implement JS markdown support",
    raw(str)
  )
}
