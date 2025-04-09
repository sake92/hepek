package ba.sake.hepek.prismjs

import scala.collection.mutable.ListBuffer
import ba.sake.hepek.scalatags.all.*
import ba.sake.hepek.html.component.CodeHighlighter
import BasePrismCodeHighlighter.*
import PrismCodeHighlightComponents.*
import CodeSource.*

private[hepek] abstract class BasePrismCodeHighlighter(
    lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    commandLine: Option[CommandLineOptions],
    isMarkup: Boolean
) extends CodeHighlighter {

  // code without any additional parameters
  override def apply(text: String): Frag =
    highlight(
      lang,
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup,
      PlainText(text)
    )

  def inline(text: String): Frag =
    highlightInline(lang, text)

  def diff(text: String): Frag =
    highlight(
      lang,
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup,
      PlainText(text)
    )

  /** Fetches a file from url via AJAX. */
  def ajax(url: String): Frag =
    highlight(
      lang,
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup,
      AJAX(url)
    )

  /** Fetches a file from url via JSONP. Supported sites: Github, Gist, Bitbucket <br> Optionally
    * provide gist fileName
    */
  def jsonp(url: String, fileName: Option[String] = None): Frag =
    highlight(
      lang,
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup,
      JSONP(url, fileName)
    )

  /** @param id Id of the Gist, e.g. "65a82e76597f2fb6c2af" */
  def gist(id: String, fileName: Option[String] = None): Frag =
    jsonp(s"https://api.github.com/gists/$id", fileName)

  /** @param user
    *   Github user, e.g. "TheAdnan"
    * @param repo
    *   User's repo, e.g. "focustube"
    * @param filePath
    *   Path of the file, e.g. "bla/index.js"
    */
  def github(user: String, repo: String, filePath: String): Frag =
    jsonp(s"https://api.github.com/repos/$user/$repo/contents/$filePath")
}

object BasePrismCodeHighlighter {

  /* does ALL THE JOB :D */
  private def highlight(
      lang: String,
      lineNumbers: Option[Int],
      lineHighlight: Option[(String, Int)],
      commandLine: Option[CommandLineOptions],
      isMarkup: Boolean, // via unescaped-markup plugin
      codeSource: CodeSource,
      isDiff: Boolean = false
  ): Frag = {
    // additional classes and attributes
    val classes = ListBuffer.empty[String]
    val attrs   = ListBuffer.empty[Modifier]
    // command-line
    commandLine.foreach { cl =>
      classes += "command-line"
      cl.outputLines.foreach { o =>
        attrs += (data.output := o)
      }
      cl.prefix match
        case CommandLineOptions.Prefix.UserHost(user, host) =>
          attrs += (data.user := user)
          attrs += (data.host := host)
        case CommandLineOptions.Prefix.Custom(commandLinePrompt) =>
          attrs += (data.prompt := commandLinePrompt)
    }
    // line highlight or CMD output
    lineHighlight.foreach { case (lh, o) =>
      attrs += (data.line        := lh)
      attrs += (data.line.offset := o)
    }
    // line numbers
    lineNumbers.foreach { lineNumsStart =>
      classes += "line-numbers"
      attrs += (data.start := lineNumsStart.toString)
    }
    // final result
    val languageClass = if isDiff then s"language-diff-$lang diff-highlight" else s"language-$lang"
    codeSource match {
      case PlainText(text) =>
        val content: Frag = if (isMarkup) {
          val unindentedMarkup =
            ba.sake.hepek.utils.StringUtils.unindent(text).trim
          // https://prismjs.com/plugins/unescaped-markup/#how-to-use
          raw(s"<!--$unindentedMarkup-->")
        } else {
          text
        }
        pre(classes.map(cls := _).toSeq, attrs.toSeq)(
          code(cls := languageClass)(content)
        )
      case AJAX(url) =>
        attrs += (data.src := url)
        pre(cls            := languageClass, classes.map(cls := _).toSeq, attrs.toSeq)
      case JSONP(url, fileName) =>
        attrs += (data.jsonp := url)
        fileName.foreach { fn =>
          attrs += (data.filename := fn)
        }
        pre(cls := languageClass, classes.map(cls := _).toSeq, attrs.toSeq)
    }
  }

  def highlightInline(lang: String, content: String): Frag =
    code(cls := s"language-$lang")(content)
}
