package ba.sake.hepek.html.statik

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.utils.StringUtils

trait BlogPostPage extends StaticPage {
  def blogSettings: BlogSettings        = BlogSettings()
  def categoryPosts: List[BlogPostPage] = List.empty
}

final case class BlogSettings(
    author: Option[String] = None,
    createdDate: Option[LocalDate] = None,
    updatedDate: Option[LocalDate] = None,
    sections: List[Section] = List.empty,
    dateFormat: DateTimeFormatter = BlogSettings.DefaultDateFormat
) {
  def withDateFormat(df: DateTimeFormatter) =
    copy(dateFormat = df)

  def withDateFormat(df: String) =
    copy(dateFormat = DateTimeFormatter.ofPattern(df))

  def withAuthor(author: Option[String]): BlogSettings =
    copy(author = author)

  def withAuthor(author: String): BlogSettings =
    copy(author = Option(author))

  def withCreatedDate(createdDate: Option[LocalDate]): BlogSettings =
    copy(createdDate = createdDate)

  def withCreatedDate(createdDate: LocalDate): BlogSettings =
    copy(createdDate = Option(createdDate))

  def withUpdatedDate(updatedDate: Option[LocalDate]): BlogSettings =
    copy(updatedDate = updatedDate)

  def withUpdatedDate(updatedDate: LocalDate): BlogSettings =
    copy(updatedDate = Option(updatedDate))

  def withSections(sections: List[Section]): BlogSettings =
    copy(sections = sections)

  def withSections(sections: Section*): BlogSettings =
    copy(sections = sections.toList)
}

object BlogSettings {
  val DefaultDateFormatPattern = "dd.MM.yyyy"

  val DefaultDateFormat = DateTimeFormatter.ofPattern(DefaultDateFormatPattern)
}

/** Handy class for sectioning a page (usually blog post). <br> Also useful for generating a TOC
  * (Table Of Contents).
  */
final case class Section(
    name: String,
    content: Frag,
    children: List[Section] = List.empty
)(using owner: RelativePath) {

  def withChildren(children: Section*) = copy(children = children.toList)

  def id: String = StringUtils.urlify(name)

  /** Refer to section only by its path, e.g. Index.mySection.ref */
  def ref(using caller: RelativePath): String =
    if (owner == caller) s"#${id}"
    else caller.relTo(owner) + s"#${id}"
}
