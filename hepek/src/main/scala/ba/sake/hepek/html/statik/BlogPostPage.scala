package ba.sake.hepek.html.statik

import java.time.format.DateTimeFormatter
import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.utils.StringUtils
import ba.sake.stone.Wither

trait BlogPostPage extends StaticPage {
  def blogSettings: BlogSettings        = BlogSettings()
  def categoryPosts: List[BlogPostPage] = List.empty
}

@Wither
final case class BlogSettings(
    author: Option[String] = None,
    createdDate: Option[LocalDate] = None,
    updatedDate: Option[LocalDate] = None,
    sections: List[Section] = List.empty,
    dateFormat: DateTimeFormatter = BlogSettings.DefaultDateFormat
) {
  def withDateFormat(df: DateTimeFormatter): BlogSettings = copy(dateFormat = df)
  def withDateFormat(df: String): BlogSettings            = copy(dateFormat = DateTimeFormatter.ofPattern(df))
}

object BlogSettings {
  val DefaultDateFormatPattern = "dd.MM.yyyy"

  val DefaultDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern(DefaultDateFormatPattern)
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
  def withChildren(children: Section*): Section = copy(children = children.toList)

  def id: String = StringUtils.urlify(name)

  /** Refer to section only by its path, e.g. Index.mySection.ref */
  def ref(implicit caller: RelativePath): String =
    if (owner == caller) "#" + id
    else caller.relTo(owner) + "#" + id
}
