package ba.sake.hepek.plain

import ba.sake.hepek.html._
import ba.sake.hepek.scalatags.all._

trait PlainPage extends HtmlPage with PlainDependencies {

  override def bodyContent: Frag =
    div(pageContent)
}
