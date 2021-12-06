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
  def withDateFormat(df: DateTimeFormatter) = copy(dateFormat = df)
  def withDateFormat(df: String)            = copy(dateFormat = DateTimeFormatter.ofPattern(df))

  def withAuthor(author: Option[String]): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat
    )

  def withAuthor(author: String): BlogSettings =
    new BlogSettings(
      createdDate = createdDate,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat,
      author = Option(author)
    )

  def withCreatedDate(createdDate: Option[LocalDate]): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat
    )

  def withCreatedDate(createdDate: LocalDate): BlogSettings =
    new BlogSettings(
      author = author,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat,
      createdDate = Option(createdDate)
    )

  def withUpdatedDate(updatedDate: Option[LocalDate]): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat
    )

  def withUpdatedDate(updatedDate: LocalDate): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      sections = sections,
      dateFormat = dateFormat,
      updatedDate = Option(updatedDate)
    )

  def withSections(sections: List[Section]): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      updatedDate = updatedDate,
      sections = sections,
      dateFormat = dateFormat
    )

  def withSections(sections: Section*): BlogSettings =
    new BlogSettings(
      author = author,
      createdDate = createdDate,
      updatedDate = updatedDate,
      dateFormat = dateFormat,
      sections = sections.toList
    )
}

object BlogSettings {
  val DefaultDateFormatPattern = "dd.MM.yyyy"

  val DefaultDateFormat = DateTimeFormatter.ofPattern(DefaultDateFormatPattern)
}

/**
  * Handy class for sectioning a page (usually blog post). <br>
  * Also useful for generating a TOC (Table Of Contents).
  */
final case class Section(
    name: String,
    content: Frag,
    children: List[Section] = List.empty
)(
    implicit owner: RelativePath
) {
  def withChildren(children: Section*) = copy(children = children.toList)

  def id: String = StringUtils.urlify(name)

  /** Refer to section only by its path, e.g. Index.mySection.ref */
  def ref(implicit caller: RelativePath): String =
    if (owner == caller) "#" + id
    else caller.relTo(owner) + "#" + id
}
