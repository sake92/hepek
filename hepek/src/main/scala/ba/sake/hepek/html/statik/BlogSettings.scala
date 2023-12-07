package ba.sake.hepek.html.statik

import java.time.format.DateTimeFormatter
import java.time.*

final class BlogSettings private (
    val author: Option[String] = None,
    val createdDate: Option[LocalDate] = None,
    val updatedDate: Option[LocalDate] = None,
    val sections: List[Section] = List.empty,
    val dateFormat: DateTimeFormatter = BlogSettings.DefaultDateFormat
) {

  def withDateFormat(df: DateTimeFormatter) = copy(dateFormat = df)

  def withDateFormat(df: String) = copy(dateFormat = DateTimeFormatter.ofPattern(df))

  def withAuthor(author: Option[String]): BlogSettings = copy(author = author)

  def withAuthor(author: String): BlogSettings = withAuthor(Option(author))

  def withCreatedDate(createdDate: Option[LocalDate]): BlogSettings =
    copy(createdDate = createdDate)

  def withCreatedDate(createdDate: LocalDate): BlogSettings =
    copy(createdDate = Option(createdDate))

  def withUpdatedDate(updatedDate: Option[LocalDate]): BlogSettings =
    copy(updatedDate = updatedDate)

  def withUpdatedDate(updatedDate: LocalDate): BlogSettings = withUpdatedDate(Option(updatedDate))

  def withSections(sections: List[Section]): BlogSettings = copy(sections = sections)

  def withSections(sections: Section*): BlogSettings = withSections(sections.toList)

  private def copy(
      author: Option[String] = author,
      createdDate: Option[LocalDate] = createdDate,
      updatedDate: Option[LocalDate] = updatedDate,
      sections: List[Section] = sections,
      dateFormat: DateTimeFormatter = dateFormat
  ) = new BlogSettings(author, createdDate, updatedDate, sections, dateFormat)

}

object BlogSettings {
  private val DefaultDateFormatPattern: String = "dd.MM.yyyy"
  private val DefaultDateFormat = DateTimeFormatter.ofPattern(DefaultDateFormatPattern)

  val default: BlogSettings = new BlogSettings(
    author = None,
    createdDate = None,
    updatedDate = None,
    sections = List.empty,
    dateFormat = DefaultDateFormat
  )

}
