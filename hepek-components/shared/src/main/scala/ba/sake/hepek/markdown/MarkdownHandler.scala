package ba.sake.hepek.markdown

import ba.sake.hepek.scalatags.Frag

trait MarkdownHandler {

  /** @param str
    * @param suppressHTML
    * @param escapeHTML
    *   set false for untrusted user input (server-side)
    * @return
    */
  def render(str: String, suppressHTML: Boolean, escapeHTML: Boolean = false): Frag
}
