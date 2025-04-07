package ba.sake.hepek.html

/** @param suppressHTML
  *   Remove HTML tags completely, handy for untrusted user input (server-side)
  * @param escapeHTML
  *   Escape HTML tags, handy for untrusted user input (server-side)
  */
final class MarkdownSettings private (
    val suppressHTML: Boolean,
    val escapeHTML: Boolean
) {

  def withSuppressHTML(suppressHTML: Boolean): MarkdownSettings =
    copy(suppressHTML = suppressHTML)

  def withEscapeHTML(escapeHTML: Boolean): MarkdownSettings =
    copy(escapeHTML = escapeHTML)

  private def copy(
      suppressHTML: Boolean = suppressHTML,
      escapeHTML: Boolean = escapeHTML
  ) = new MarkdownSettings(suppressHTML, escapeHTML)
}

object MarkdownSettings {
  def apply(): MarkdownSettings =
    new MarkdownSettings(false, false)
}
