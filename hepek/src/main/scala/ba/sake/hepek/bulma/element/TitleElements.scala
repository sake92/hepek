package ba.sake.hepek.bulma.element

import ba.sake.hepek.bulma.BulmaElement
import scalatags.Text.all._

case class BulmaTitle(inner: Frag, size: Int = 3) extends BulmaElement {

  override def content: Frag =
    h1(cls := s"title is-$size ")(inner)
}

case class BulmaSubtitle(inner: Frag, size: Int = 5) extends BulmaElement {

  override def content: Frag =
    h2(cls := s"subtitle is-$size ")(inner)
}

object TitleElements extends TitleElements

trait TitleElements {

  def title(content: Frag, size: Int = 3) =
    BulmaTitle(content, size).content

  def subtitle(content: Frag, size: Int = 5) =
    BulmaSubtitle(content, size).content
}
