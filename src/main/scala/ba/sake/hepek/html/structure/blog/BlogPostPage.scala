package ba.sake.hepek.html

package structure

package blog

import java.time.format.DateTimeFormatter
import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.utils.StringUtils

trait BlogPostPage extends StaticPage {

  // TODO BlogSettings

  def postAuthor: Option[String] = None

  def postCreateDate: Option[LocalDate] = None

  def postSections: List[Section] = List.empty

  def categoryPosts: List[BlogPostPage] = List.empty

  def dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
}

/**
  * Handy class for sectioning a page (usually blog post). <br>
  * Also useful for generating a TOC (Table Of Contents).
  */
case class Section(
    name: String,
    content: Frag,
    children: List[Section] = List.empty
)(
    implicit owner: RelativePath
) {

  def id: String = StringUtils.urlify(name)

  /** Refer to section only by its path, e.g. Index.mySection.ref */
  def ref(implicit caller: RelativePath): String =
    if (owner == caller) "#" + id
    else caller.relTo(owner) + "#" + id
}
