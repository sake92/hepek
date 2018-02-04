package ba.sake.hepek.prismjs

import scala.collection.mutable.ListBuffer
import scalatags.Text.all._
import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.html.component.CodeHighlighter

object PrismCodeHighlightComponents extends PrismCodeHighlightComponents {

  // not every plugin has CSS, so tuples (pluginName, hasCSS)
  val prismPlugins: List[(String, Boolean)] = List(
    "autolinker"           -> true,
    "command-line"         -> true,
    "data-uri-highlight"   -> false,
    "file-highlight"       -> false,
    "jsonp-highlight"      -> false,
    "keep-markup"          -> false,
    "line-highlight"       -> true,
    "line-numbers"         -> true,
    "normalize-whitespace" -> false,
    "previewers"           -> true,
    "toolbar"              -> true,
    "show-language"        -> false, // must come after toolbar
    "copy-to-clipboard"    -> false, // must come after toolbar
    "unescaped-markup"     -> true,
    "wpd"                  -> true
  )

  sealed trait CodeSource

  object CodeSource {
    case class PlainText(text: String)                      extends CodeSource
    case class AJAX(url: String)                            extends CodeSource // any file on the web
    case class JSONP(url: String, fileName: Option[String]) extends CodeSource // Github, Gist, Bitbucket
  }
}

trait PrismCodeHighlightComponents extends CodeHighlightComponents {

  def showLineNumbers: Boolean = true

  override def abap = PrismCodeHighlighter("abap", showLineNumbers)
  override def actionscript =
    PrismCodeHighlighter("actionscript", showLineNumbers)
  override def ada        = PrismCodeHighlighter("ada", showLineNumbers)
  override def apacheconf = PrismCodeHighlighter("apacheconf", showLineNumbers)
  override def apl        = PrismCodeHighlighter("apl", showLineNumbers)
  override def applescript =
    PrismCodeHighlighter("applescript", showLineNumbers)
  override def arduino    = PrismCodeHighlighter("arduino", showLineNumbers)
  override def asciidoc   = PrismCodeHighlighter("asciidoc", showLineNumbers)
  override def asm6502    = PrismCodeHighlighter("asm6502", showLineNumbers)
  override def aspnet     = PrismCodeHighlighter("aspnet", showLineNumbers)
  override def autohotkey = PrismCodeHighlighter("autohotkey", showLineNumbers)
  override def autoit     = PrismCodeHighlighter("autoit", showLineNumbers)
  override def bash       = PrismCmdHighlighter("bash", showLineNumbers)
  override def basic      = PrismCodeHighlighter("basic", showLineNumbers)
  override def batch      = PrismCmdHighlighter("batch", showLineNumbers)
  override def bison      = PrismCodeHighlighter("bison", showLineNumbers)
  override def brainfuck  = PrismCodeHighlighter("brainfuck", showLineNumbers)
  override def bro        = PrismCodeHighlighter("bro", showLineNumbers)
  override def c          = PrismCodeHighlighter("c", showLineNumbers)
  override def clike      = PrismCodeHighlighter("clike", showLineNumbers)
  override def coffeescript =
    PrismCodeHighlighter("coffeescript", showLineNumbers)
  override def cpp        = PrismCodeHighlighter("cpp", showLineNumbers)
  override def crystal    = PrismCodeHighlighter("crystal", showLineNumbers)
  override def csharp     = PrismCodeHighlighter("csharp", showLineNumbers)
  override def css        = PrismCodeHighlighter("css", showLineNumbers)
  override def cssExtras  = PrismCodeHighlighter("css-extras", showLineNumbers)
  override def d          = PrismCodeHighlighter("d", showLineNumbers)
  override def dart       = PrismCodeHighlighter("dart", showLineNumbers)
  override def diff       = PrismCodeHighlighter("diff", showLineNumbers)
  override def django     = PrismCodeHighlighter("django", showLineNumbers)
  override def docker     = PrismCodeHighlighter("docker", showLineNumbers)
  override def eiffel     = PrismCodeHighlighter("eiffel", showLineNumbers)
  override def elixir     = PrismCodeHighlighter("elixir", showLineNumbers)
  override def elm        = PrismCodeHighlighter("elm", showLineNumbers)
  override def erlang     = PrismCodeHighlighter("erlang", showLineNumbers)
  override def flow       = PrismCodeHighlighter("flow", showLineNumbers)
  override def fortran    = PrismCodeHighlighter("fortran", showLineNumbers)
  override def fsharp     = PrismCodeHighlighter("fsharp", showLineNumbers)
  override def gherkin    = PrismCodeHighlighter("gherkin", showLineNumbers)
  override def git        = PrismCodeHighlighter("git", showLineNumbers)
  override def glsl       = PrismCodeHighlighter("glsl", showLineNumbers)
  override def go         = PrismCodeHighlighter("go", showLineNumbers)
  override def graphql    = PrismCodeHighlighter("graphql", showLineNumbers)
  override def groovy     = PrismCodeHighlighter("groovy", showLineNumbers)
  override def haml       = PrismCodeHighlighter("haml", showLineNumbers)
  override def handlebars = PrismCodeHighlighter("handlebars", showLineNumbers)
  override def haskell    = PrismCodeHighlighter("haskell", showLineNumbers)
  override def haxe       = PrismCodeHighlighter("haxe", showLineNumbers)
  override def http       = PrismCodeHighlighter("http", showLineNumbers)
  override def ichigojam  = PrismCodeHighlighter("ichigojam", showLineNumbers)
  override def icon       = PrismCodeHighlighter("icon", showLineNumbers)
  override def inform7    = PrismCodeHighlighter("inform7", showLineNumbers)
  override def ini        = PrismCodeHighlighter("ini", showLineNumbers)
  override def io         = PrismCodeHighlighter("io", showLineNumbers)
  override def j          = PrismCodeHighlighter("j", showLineNumbers)
  override def java       = PrismCodeHighlighter("java", showLineNumbers)
  override def javascript = PrismCodeHighlighter("javascript", showLineNumbers)
  override def jolie      = PrismCodeHighlighter("jolie", showLineNumbers)
  override def json       = PrismCodeHighlighter("json", showLineNumbers)
  override def jsx        = PrismCodeHighlighter("jsx", showLineNumbers)
  override def julia      = PrismCodeHighlighter("julia", showLineNumbers)
  override def keyman     = PrismCodeHighlighter("keyman", showLineNumbers)
  override def kotlin     = PrismCodeHighlighter("kotlin", showLineNumbers)
  override def latex      = PrismCodeHighlighter("latex", showLineNumbers)
  override def less       = PrismCodeHighlighter("less", showLineNumbers)
  override def livescript = PrismCodeHighlighter("livescript", showLineNumbers)
  override def lolcode    = PrismCodeHighlighter("lolcode", showLineNumbers)
  override def lua        = PrismCodeHighlighter("lua", showLineNumbers)
  override def makefile   = PrismCodeHighlighter("makefile", showLineNumbers)
  override def markdown   = PrismCodeHighlighter("markdown", showLineNumbers)
  override def markup     = PrismCodeHighlighter("markup", showLineNumbers, true)
  override def matlab     = PrismCodeHighlighter("matlab", showLineNumbers)
  override def mel        = PrismCodeHighlighter("mel", showLineNumbers)
  override def mizar      = PrismCodeHighlighter("mizar", showLineNumbers)
  override def monkey     = PrismCodeHighlighter("monkey", showLineNumbers)
  override def n4js       = PrismCodeHighlighter("n4js", showLineNumbers)
  override def nasm       = PrismCodeHighlighter("nasm", showLineNumbers)
  override def nginx      = PrismCodeHighlighter("nginx", showLineNumbers)
  override def nim        = PrismCodeHighlighter("nim", showLineNumbers)
  override def nix        = PrismCodeHighlighter("nix", showLineNumbers)
  override def nsis       = PrismCodeHighlighter("nsis", showLineNumbers)
  override def objectivec = PrismCodeHighlighter("objectivec", showLineNumbers)
  override def ocaml      = PrismCodeHighlighter("ocaml", showLineNumbers)
  override def opencl     = PrismCodeHighlighter("opencl", showLineNumbers)
  override def oz         = PrismCodeHighlighter("oz", showLineNumbers)
  override def parigp     = PrismCodeHighlighter("parigp", showLineNumbers)
  override def parser     = PrismCodeHighlighter("parser", showLineNumbers)
  override def pascal     = PrismCodeHighlighter("pascal", showLineNumbers)
  override def perl       = PrismCodeHighlighter("perl", showLineNumbers)
  override def php        = PrismCodeHighlighter("php", showLineNumbers)
  override def phpExtras  = PrismCodeHighlighter("php-extras", showLineNumbers)
  override def powershell = PrismCmdHighlighter("powershell", showLineNumbers)
  override def processing = PrismCodeHighlighter("processing", showLineNumbers)
  override def prolog     = PrismCodeHighlighter("prolog", showLineNumbers)
  override def properties = PrismCodeHighlighter("properties", showLineNumbers)
  override def protobuf   = PrismCodeHighlighter("protobuf", showLineNumbers)
  override def pug        = PrismCodeHighlighter("pug", showLineNumbers)
  override def puppet     = PrismCodeHighlighter("puppet", showLineNumbers)
  override def pure       = PrismCodeHighlighter("pure", showLineNumbers)
  override def python     = PrismCodeHighlighter("python", showLineNumbers)
  override def q          = PrismCodeHighlighter("q", showLineNumbers)
  override def qore       = PrismCodeHighlighter("qore", showLineNumbers)
  override def r          = PrismCodeHighlighter("r", showLineNumbers)
  override def reason     = PrismCodeHighlighter("reason", showLineNumbers)
  override def renpy      = PrismCodeHighlighter("renpy", showLineNumbers)
  override def rest       = PrismCodeHighlighter("rest", showLineNumbers)
  override def rip        = PrismCodeHighlighter("rip", showLineNumbers)
  override def roboconf   = PrismCodeHighlighter("roboconf", showLineNumbers)
  override def ruby       = PrismCodeHighlighter("ruby", showLineNumbers)
  override def rust       = PrismCodeHighlighter("rust", showLineNumbers)
  override def sas        = PrismCodeHighlighter("sas", showLineNumbers)
  override def sass       = PrismCodeHighlighter("sass", showLineNumbers)
  override def scala      = PrismCodeHighlighter("scala", showLineNumbers)
  override def scheme     = PrismCodeHighlighter("scheme", showLineNumbers)
  override def scss       = PrismCodeHighlighter("scss", showLineNumbers)
  override def smalltalk  = PrismCodeHighlighter("smalltalk", showLineNumbers)
  override def smarty     = PrismCodeHighlighter("smarty", showLineNumbers)
  override def sql        = PrismCodeHighlighter("sql", showLineNumbers)
  override def stylus     = PrismCodeHighlighter("stylus", showLineNumbers)
  override def swift      = PrismCodeHighlighter("swift", showLineNumbers)
  override def tcl        = PrismCodeHighlighter("tcl", showLineNumbers)
  override def textile    = PrismCodeHighlighter("textile", showLineNumbers)
  override def twig       = PrismCodeHighlighter("twig", showLineNumbers)
  override def typescript = PrismCodeHighlighter("typescript", showLineNumbers)
  override def vbnet      = PrismCodeHighlighter("vbnet", showLineNumbers)
  override def verilog    = PrismCodeHighlighter("verilog", showLineNumbers)
  override def vhdl       = PrismCodeHighlighter("vhdl", showLineNumbers)
  override def vim        = PrismCodeHighlighter("vim", showLineNumbers)
  override def wiki       = PrismCodeHighlighter("wiki", showLineNumbers)
  override def xeora      = PrismCodeHighlighter("xeora", showLineNumbers)
  override def xojo       = PrismCodeHighlighter("xojo", showLineNumbers)
  override def yaml       = PrismCodeHighlighter("yaml", showLineNumbers)

}

/* "normal" code highlighter */
object PrismCodeHighlighter {

  def apply(lang: String,
            showLineNumbers: Boolean,
            isMarkup: Boolean = false): PrismCodeHighlighter = {
    val lineNums = if (showLineNumbers) Option(1) else None
    PrismCodeHighlighter(lang, lineNums, None, isMarkup)
  }
}

case class PrismCodeHighlighter(
    lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    isMarkup: Boolean
) extends BaseCodeHighlighter(
      lang,
      isMarkup,
      lineNumbers,
      lineHighlight,
      None
    ) {

  def withLineNumsStart(startFrom: Int) =
    this.copy(lineNumbers = Option(startFrom))

  def withLineHighlight(lines: String, offset: Int = 0) =
    this.copy(lineHighlight = Option(lines -> offset))
}

/* command-line code highlighter */
object PrismCmdHighlighter {

  def apply(lang: String, showLineNumbers: Boolean): PrismCmdHighlighter = {
    val lineNums = if (showLineNumbers) Option(1) else None
    PrismCmdHighlighter(lang, lineNums, None, None)
  }
}

case class PrismCmdHighlighter(
    lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    commandLine: Option[(Either[(String, String), String], Option[String])]
) extends BaseCodeHighlighter(
      lang,
      false,
      lineNumbers,
      lineHighlight,
      commandLine
    ) {

  def withLineNumsStart(startFrom: Int) =
    this.copy(lineNumbers = Option(startFrom))

  def withLineHighlight(lines: String, offset: Int = 0) =
    this.copy(lineHighlight = Option(lines -> offset))

  def withCmdUser(cmdUser: String,
                  cmdHost: String = "localhost",
                  outputLines: Option[String]) = {
    val cmdLine = Left(cmdUser, cmdHost) -> outputLines
    this.copy(commandLine = Option(cmdLine))
  }

  def withCmdPrompt(cmdPrompt: String, outputLines: Option[String]) = {
    val cmdLine = Right(cmdPrompt) -> outputLines
    this.copy(commandLine = Option(cmdLine))
  }
}

object BaseCodeHighlighter {
  import PrismCodeHighlightComponents._
  import CodeSource._

  /* does ALL THE JOB :D */
  private def highlight(
      lang: String,
      codeSource: CodeSource,
      lineNumbers: Option[Int],
      lineHighlight: Option[(String, Int)],
      commandLine: Option[(Either[(String, String), String], Option[String])],
      isMarkup: Boolean
  ): Frag = {
    // additional classes and attributes
    val classes = ListBuffer.empty[String]
    val attrs   = ListBuffer.empty[Modifier]
    // command-line
    commandLine.foreach {
      case (commandLinePrefix, maybeOutputLines) =>
        classes += "command-line"
        maybeOutputLines.foreach { o =>
          attrs += (data.output := o)
        }
        commandLinePrefix.fold(
          {
            case (commandLineUser, commandLineHost) =>
              attrs += (data.user := commandLineUser)
              attrs += (data.host := commandLineHost)
          }, { commandLinePrompt =>
            attrs += (data.prompt := commandLinePrompt)
          }
        )
    }
    // line highlight
    lineHighlight.foreach {
      case (lh, o) =>
        attrs += (data.line := lh)
        attrs += (data.line.offset := o)
    }
    // line numbers
    lineNumbers.foreach { lineNumsStart =>
      classes += "line-numbers"
      attrs += (data.start := lineNumsStart.toString)
    }
    // final result
    val classesString = classes.mkString(" ")
    codeSource match {
      case PlainText(text) => {
        val content: Frag = if (isMarkup) raw(s"<!-- $text -->") else text
        pre(cls := classesString, attrs)(
          code(cls := s"language-$lang")(content)
        )
      }
      case AJAX(url) => {
        attrs += (data.src := url)
        pre(cls := classesString, attrs)
      }
      case JSONP(url, fileName) => {
        attrs += (data.jsonp := url)
        fileName.foreach { fn =>
          attrs += (data.filename := fn)
        }
        pre(cls := classesString, attrs)
      }
    }
  }

  def highlightInline(lang: String, content: String): Frag =
    code(cls := s"language-$lang")(content)
}

abstract class BaseCodeHighlighter(
    lang: String,
    isMarkup: Boolean,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    commandLine: Option[(Either[(String, String), String], Option[String])]
) extends CodeHighlighter {
  import BaseCodeHighlighter._
  import PrismCodeHighlightComponents._
  import CodeSource._

  // code without any additional parameters
  override def apply(text: String): Frag =
    highlight(
      lang,
      PlainText(text),
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup
    )

  def inline(text: String): Frag =
    highlightInline(lang, text)

  /** Fetches a file from url via AJAX. */
  def ajax(url: String): Frag =
    highlight(
      lang,
      AJAX(url),
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup
    )

  /** Fetches a file from url via JSONP. Supported sites: Github, Gist, Bitbucket <br>
    *  Optionally provide gist fileName */
  def jsonp(url: String, fileName: Option[String] = None): Frag =
    highlight(
      lang,
      JSONP(url, fileName),
      lineNumbers,
      lineHighlight,
      commandLine,
      isMarkup
    )
}
