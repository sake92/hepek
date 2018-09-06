package ba.sake.hepek.html

package structure

package blog

import java.time.format.DateTimeFormatter
import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.utils.StringUtils

trait BlogPostPage extends StaticPage {
  def blogSettings: BlogSettings        = BlogSettings()
  def categoryPosts: List[BlogPostPage] = List.empty
}

case class BlogSettings(
    author: Option[String] = None,
    createDate: Option[LocalDate] = None,
    sections: List[Section] = List.empty,
    dateFormat: DateTimeFormatter = BlogSettings.DefaultDateFormat
) {
  def withAuthor(author: String)                    = copy(author = Some(author))
  def withAuthor(author: Option[String])            = copy(author = author)
  def withCreateDate(createDate: LocalDate)         = copy(createDate = Some(createDate))
  def withCreateDate(createDate: Option[LocalDate]) = copy(createDate = createDate)
  def withSections(sections: List[Section])         = copy(sections = sections)
  def withSections(sections: Section*)              = copy(sections = sections.toList)
  def withDateFormat(df: DateTimeFormatter)         = copy(dateFormat = df)
  def withDateFormat(df: String)                    = copy(dateFormat = DateTimeFormatter.ofPattern(df))
}

object BlogSettings {
  val DefaultDateFormatPattern = "dd.MM.yyyy"

  val DefaultDateFormat = DateTimeFormatter.ofPattern(BlogSettings.DefaultDateFormatPattern)
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
