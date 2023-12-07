package ba.sake.hepek.plain

import ba.sake.hepek.html.*
import ba.sake.hepek.scalatags.all.*

trait PlainPage extends HtmlPage with PlainDependencies {

  override def bodyContent: Frag =
    div(pageContent)
}
