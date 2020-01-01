package ba.sake.hepek.plain

import scalatags.Text.all._
import ba.sake.hepek.html._

trait PlainPage extends HtmlPage with PlainDependencies {

  override def bodyContent: Frag =
    div(pageContent)
}
