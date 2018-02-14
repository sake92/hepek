package ba.sake.hepek.html.component

import scalatags.Text.all._

object BasicComponents extends BasicComponents {
  case class MarkdownRule(regex: String, replacement: String)
  // \R is newline (\r or \n or \r\n)
  private def markdownRules = List(
    MarkdownRule("(\\R(\\s*)){2,}", "<br><br>"), // new paragraph
    MarkdownRule("(\\S)([ ]{2,})\\R", "$1<br>"), // newline
    MarkdownRule("""(\*\*)(.*?)\1""", "<strong>$2</strong>"), // bold
    MarkdownRule("""(\*)(.*?)\1""", "<em>$2</em>"), // emphasis
    MarkdownRule("""(__)(.*?)\1""", "<u>$2</u>"), // underline
    MarkdownRule("""\~\~(.*?)\~\~""", "<del>$1</del>") // del
  )
}

trait BasicComponents {
  import BasicComponents._

  /** Anchor link : <a href="hreff"> */
  def hyperlink(hreff: String, newWindow: Boolean = false): Frag = {
    val optParams = if (newWindow) List(target := "_blank") else List()
    a(href := hreff, optParams)
  }

  /**
    * - newline: two spaces on the end of line OR two+ new lines
    * - bold: **text**
    * - italic: *text*
    * - underline: __text__
    * - strikethrough: ~~text~~
    */
  def md(s: String): Frag = {
    var result = s
    markdownRules.foreach { r =>
      result = result.replaceAll(r.regex, r.replacement)
    }
    raw(result)
  }

}
