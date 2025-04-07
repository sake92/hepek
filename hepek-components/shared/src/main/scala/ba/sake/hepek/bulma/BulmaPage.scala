package ba.sake.hepek.bulma

import ba.sake.hepek.html.*
import ba.sake.hepek.markdown.MarkdownHandler
import ba.sake.hepek.bulma.markdown.BulmaMarkdownHandler

trait BulmaPage extends HtmlPage with BulmaDependencies {
  override val markdownHandler: MarkdownHandler = BulmaMarkdownHandler()
}
