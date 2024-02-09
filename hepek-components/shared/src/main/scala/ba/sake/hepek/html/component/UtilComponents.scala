package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

trait UtilComponents {

  /** Remove HTML tags completely, handy for untrusted user input (server-side) */
  def suppressHTML: Boolean = false

  /** Escape HTML tags, handy for untrusted user input (server-side) */
  def escapeHTML: Boolean = false

  extension (str: String) def md: Frag
}
