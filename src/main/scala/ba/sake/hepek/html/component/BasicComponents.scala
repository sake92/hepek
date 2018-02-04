package ba.sake.hepek.html.component

import scalatags.Text.all._

object BasicComponents extends BasicComponents {
  case class MarkdownRule(regex: String, replacement: String)
  // thx https://gist.github.com/renehamburger/12f14a9bd9297394e5bd
  // TODO add more ?
  private def markdownRules = List(
    MarkdownRule("""([ ]{2,}\n)|([\n]{2,})""", "<br>"), // newline
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
    p(raw(result))
  }

}
