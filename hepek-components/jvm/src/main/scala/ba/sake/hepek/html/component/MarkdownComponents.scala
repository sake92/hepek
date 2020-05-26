package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.Frag

trait MarkdownComponents { self =>
  def md(str: String): Frag

  implicit class MarkdownComponentOps(private val str: String) {
    def md: Frag = self.md(str)
  }
}
