package ba.sake.hepek.prismjs

import scala.collection.mutable.ListBuffer

import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.html.component.CodeHighlighter
import ba.sake.hepek.scalatags.all._

object PrismCodeHighlightComponents extends PrismCodeHighlightComponents {
  sealed trait CodeSource

  object CodeSource {
    final case class PlainText(text: String)                      extends CodeSource
    final case class AJAX(url: String)                            extends CodeSource // any file on the web
    final case class JSONP(url: String, fileName: Option[String]) extends CodeSource // Github, Gist, Bitbucket
  }
}

trait PrismCodeHighlightComponents extends CodeHighlightComponents {
  def showLineNumbers: Boolean = true

  override def abap: PrismCodeHighlighter = PrismCodeHighlighter("abap", showLineNumbers)

  override def actionscript: PrismCodeHighlighter =
    PrismCodeHighlighter("actionscript", showLineNumbers)
  override def ada: PrismCodeHighlighter = PrismCodeHighlighter("ada", showLineNumbers)
  override def apacheconf: PrismCodeHighlighter = PrismCodeHighlighter("apacheconf", showLineNumbers)
  override def apl: PrismCodeHighlighter = PrismCodeHighlighter("apl", showLineNumbers)

  override def applescript: PrismCodeHighlighter =
    PrismCodeHighlighter("applescript", showLineNumbers)
  override def arduino: PrismCodeHighlighter = PrismCodeHighlighter("arduino", showLineNumbers)
  override def asciidoc: PrismCodeHighlighter = PrismCodeHighlighter("asciidoc", showLineNumbers)
  override def asm6502    = PrismCodeHighlighter("asm6502", showLineNumbers)
  override def aspnet: PrismCodeHighlighter = PrismCodeHighlighter("aspnet", showLineNumbers)
  override def autohotkey: PrismCodeHighlighter = PrismCodeHighlighter("autohotkey", showLineNumbers)
  override def autoit: PrismCodeHighlighter = PrismCodeHighlighter("autoit", showLineNumbers)
  override def bash :PrismCmdHighlighter      = PrismCmdHighlighter("bash")
  override def basic: PrismCodeHighlighter = PrismCodeHighlighter("basic", showLineNumbers)
  override def batch  :PrismCmdHighlighter     = PrismCmdHighlighter("batch")
  override def bison: PrismCodeHighlighter = PrismCodeHighlighter("bison", showLineNumbers)
  override def brainfuck: PrismCodeHighlighter = PrismCodeHighlighter("brainfuck", showLineNumbers)
  override def bro: PrismCodeHighlighter = PrismCodeHighlighter("bro", showLineNumbers)
  override def c: PrismCodeHighlighter = PrismCodeHighlighter("c", showLineNumbers)
  override def clike: PrismCodeHighlighter = PrismCodeHighlighter("clike", showLineNumbers)

  override def coffeescript: PrismCodeHighlighter =
    PrismCodeHighlighter("coffeescript", showLineNumbers)
  override def cpp: PrismCodeHighlighter = PrismCodeHighlighter("cpp", showLineNumbers)
  override def crystal: PrismCodeHighlighter = PrismCodeHighlighter("crystal", showLineNumbers)
  override def csharp: PrismCodeHighlighter = PrismCodeHighlighter("csharp", showLineNumbers)
  override def css: PrismCodeHighlighter = PrismCodeHighlighter("css", showLineNumbers)
  override def cssExtras: PrismCodeHighlighter = PrismCodeHighlighter("css-extras", showLineNumbers)
  override def csp: PrismCodeHighlighter = PrismCodeHighlighter("csp", showLineNumbers)
  override def d: PrismCodeHighlighter = PrismCodeHighlighter("d", showLineNumbers)
  override def dart: PrismCodeHighlighter = PrismCodeHighlighter("dart", showLineNumbers)
  override def diff: PrismCodeHighlighter = PrismCodeHighlighter("diff", showLineNumbers)
  override def django: PrismCodeHighlighter = PrismCodeHighlighter("django", showLineNumbers)
  override def docker: PrismCodeHighlighter = PrismCodeHighlighter("docker", showLineNumbers)
  override def eiffel: PrismCodeHighlighter = PrismCodeHighlighter("eiffel", showLineNumbers)
  override def elixir: PrismCodeHighlighter = PrismCodeHighlighter("elixir", showLineNumbers)
  override def elm: PrismCodeHighlighter = PrismCodeHighlighter("elm", showLineNumbers)
  override def erlang: PrismCodeHighlighter = PrismCodeHighlighter("erlang", showLineNumbers)
  override def flow: PrismCodeHighlighter = PrismCodeHighlighter("flow", showLineNumbers)
  override def fortran: PrismCodeHighlighter = PrismCodeHighlighter("fortran", showLineNumbers)
  override def fsharp: PrismCodeHighlighter = PrismCodeHighlighter("fsharp", showLineNumbers)
  override def gherkin: PrismCodeHighlighter = PrismCodeHighlighter("gherkin", showLineNumbers)
  override def git: PrismCodeHighlighter = PrismCodeHighlighter("git", showLineNumbers)
  override def glsl: PrismCodeHighlighter = PrismCodeHighlighter("glsl", showLineNumbers)
  override def go: PrismCodeHighlighter = PrismCodeHighlighter("go", showLineNumbers)
  override def graphql: PrismCodeHighlighter = PrismCodeHighlighter("graphql", showLineNumbers)
  override def groovy: PrismCodeHighlighter = PrismCodeHighlighter("groovy", showLineNumbers)
  override def haml: PrismCodeHighlighter = PrismCodeHighlighter("haml", showLineNumbers)
  override def handlebars: PrismCodeHighlighter = PrismCodeHighlighter("handlebars", showLineNumbers)
  override def haskell: PrismCodeHighlighter = PrismCodeHighlighter("haskell", showLineNumbers)
  override def haxe: PrismCodeHighlighter = PrismCodeHighlighter("haxe", showLineNumbers)
  override def hpkp: PrismCodeHighlighter = PrismCodeHighlighter("hpkp", showLineNumbers)
  override def hsts: PrismCodeHighlighter = PrismCodeHighlighter("hsts", showLineNumbers)
  override def http: PrismCodeHighlighter = PrismCodeHighlighter("http", showLineNumbers)
  override def ichigojam: PrismCodeHighlighter = PrismCodeHighlighter("ichigojam", showLineNumbers)
  override def icon: PrismCodeHighlighter = PrismCodeHighlighter("icon", showLineNumbers)
  override def inform7    = PrismCodeHighlighter("inform7", showLineNumbers)
  override def ini: PrismCodeHighlighter = PrismCodeHighlighter("ini", showLineNumbers)
  override def io: PrismCodeHighlighter = PrismCodeHighlighter("io", showLineNumbers)
  override def j: PrismCodeHighlighter = PrismCodeHighlighter("j", showLineNumbers)
  override def java: PrismCodeHighlighter = PrismCodeHighlighter("java", showLineNumbers)
  override def javascript: PrismCodeHighlighter = PrismCodeHighlighter("javascript", showLineNumbers)
  override def jolie: PrismCodeHighlighter = PrismCodeHighlighter("jolie", showLineNumbers)
  override def json: PrismCodeHighlighter = PrismCodeHighlighter("json", showLineNumbers)
  override def jsx: PrismCodeHighlighter = PrismCodeHighlighter("jsx", showLineNumbers)
  override def julia: PrismCodeHighlighter = PrismCodeHighlighter("julia", showLineNumbers)
  override def keyman: PrismCodeHighlighter = PrismCodeHighlighter("keyman", showLineNumbers)
  override def kotlin: PrismCodeHighlighter = PrismCodeHighlighter("kotlin", showLineNumbers)
  override def latex: PrismCodeHighlighter = PrismCodeHighlighter("latex", showLineNumbers)
  override def less: PrismCodeHighlighter = PrismCodeHighlighter("less", showLineNumbers)
  override def livescript: PrismCodeHighlighter = PrismCodeHighlighter("livescript", showLineNumbers)
  override def lolcode: PrismCodeHighlighter = PrismCodeHighlighter("lolcode", showLineNumbers)
  override def lua: PrismCodeHighlighter = PrismCodeHighlighter("lua", showLineNumbers)
  override def makefile: PrismCodeHighlighter = PrismCodeHighlighter("makefile", showLineNumbers)
  override def markdown: PrismCodeHighlighter = PrismCodeHighlighter("markdown", showLineNumbers)
  override def markup: PrismCodeHighlighter = PrismCodeHighlighter("markup", showLineNumbers, true)
  override def matlab: PrismCodeHighlighter = PrismCodeHighlighter("matlab", showLineNumbers)
  override def mel: PrismCodeHighlighter = PrismCodeHighlighter("mel", showLineNumbers)
  override def mizar: PrismCodeHighlighter = PrismCodeHighlighter("mizar", showLineNumbers)
  override def monkey: PrismCodeHighlighter = PrismCodeHighlighter("monkey", showLineNumbers)
  override def n4js       = PrismCodeHighlighter("n4js", showLineNumbers)
  override def nasm: PrismCodeHighlighter = PrismCodeHighlighter("nasm", showLineNumbers)
  override def nginx: PrismCodeHighlighter = PrismCodeHighlighter("nginx", showLineNumbers)
  override def nim: PrismCodeHighlighter = PrismCodeHighlighter("nim", showLineNumbers)
  override def nix: PrismCodeHighlighter = PrismCodeHighlighter("nix", showLineNumbers)
  override def nsis: PrismCodeHighlighter = PrismCodeHighlighter("nsis", showLineNumbers)
  override def objectivec: PrismCodeHighlighter = PrismCodeHighlighter("objectivec", showLineNumbers)
  override def ocaml: PrismCodeHighlighter = PrismCodeHighlighter("ocaml", showLineNumbers)
  override def opencl: PrismCodeHighlighter = PrismCodeHighlighter("opencl", showLineNumbers)
  override def oz: PrismCodeHighlighter = PrismCodeHighlighter("oz", showLineNumbers)
  override def parigp: PrismCodeHighlighter = PrismCodeHighlighter("parigp", showLineNumbers)
  override def parser: PrismCodeHighlighter = PrismCodeHighlighter("parser", showLineNumbers)
  override def pascal: PrismCodeHighlighter = PrismCodeHighlighter("pascal", showLineNumbers)
  override def perl: PrismCodeHighlighter = PrismCodeHighlighter("perl", showLineNumbers)
  override def php: PrismCodeHighlighter = PrismCodeHighlighter("php", showLineNumbers)
  override def phpExtras: PrismCodeHighlighter = PrismCodeHighlighter("php-extras", showLineNumbers)
  override def powershell: PrismCmdHighlighter = PrismCmdHighlighter("powershell")
  override def processing: PrismCodeHighlighter = PrismCodeHighlighter("processing", showLineNumbers)
  override def prolog: PrismCodeHighlighter = PrismCodeHighlighter("prolog", showLineNumbers)
  override def properties: PrismCodeHighlighter = PrismCodeHighlighter("properties", showLineNumbers)
  override def protobuf: PrismCodeHighlighter = PrismCodeHighlighter("protobuf", showLineNumbers)
  override def pug: PrismCodeHighlighter = PrismCodeHighlighter("pug", showLineNumbers)
  override def puppet: PrismCodeHighlighter = PrismCodeHighlighter("puppet", showLineNumbers)
  override def pure: PrismCodeHighlighter = PrismCodeHighlighter("pure", showLineNumbers)
  override def python: PrismCodeHighlighter = PrismCodeHighlighter("python", showLineNumbers)
  override def q: PrismCodeHighlighter = PrismCodeHighlighter("q", showLineNumbers)
  override def qore: PrismCodeHighlighter = PrismCodeHighlighter("qore", showLineNumbers)
  override def r: PrismCodeHighlighter = PrismCodeHighlighter("r", showLineNumbers)
  override def reason: PrismCodeHighlighter = PrismCodeHighlighter("reason", showLineNumbers)
  override def renpy: PrismCodeHighlighter = PrismCodeHighlighter("renpy", showLineNumbers)
  override def rest: PrismCodeHighlighter = PrismCodeHighlighter("rest", showLineNumbers)
  override def rip: PrismCodeHighlighter = PrismCodeHighlighter("rip", showLineNumbers)
  override def roboconf: PrismCodeHighlighter = PrismCodeHighlighter("roboconf", showLineNumbers)
  override def ruby: PrismCodeHighlighter = PrismCodeHighlighter("ruby", showLineNumbers)
  override def rust: PrismCodeHighlighter = PrismCodeHighlighter("rust", showLineNumbers)
  override def sas: PrismCodeHighlighter = PrismCodeHighlighter("sas", showLineNumbers)
  override def sass: PrismCodeHighlighter = PrismCodeHighlighter("sass", showLineNumbers)
  override def scala: PrismCodeHighlighter = PrismCodeHighlighter("scala", showLineNumbers)
  override def scheme: PrismCodeHighlighter = PrismCodeHighlighter("scheme", showLineNumbers)
  override def scss: PrismCodeHighlighter = PrismCodeHighlighter("scss", showLineNumbers)
  override def smalltalk: PrismCodeHighlighter = PrismCodeHighlighter("smalltalk", showLineNumbers)
  override def smarty: PrismCodeHighlighter = PrismCodeHighlighter("smarty", showLineNumbers)
  override def sql: PrismCodeHighlighter = PrismCodeHighlighter("sql", showLineNumbers)
  override def stylus: PrismCodeHighlighter = PrismCodeHighlighter("stylus", showLineNumbers)
  override def swift: PrismCodeHighlighter = PrismCodeHighlighter("swift", showLineNumbers)
  override def tcl: PrismCodeHighlighter = PrismCodeHighlighter("tcl", showLineNumbers)
  override def textile: PrismCodeHighlighter = PrismCodeHighlighter("textile", showLineNumbers)
  override def tsx: PrismCodeHighlighter = PrismCodeHighlighter("tsx", showLineNumbers)
  override def twig: PrismCodeHighlighter = PrismCodeHighlighter("twig", showLineNumbers)
  override def typescript: PrismCodeHighlighter = PrismCodeHighlighter("typescript", showLineNumbers)
  override def vbnet: PrismCodeHighlighter = PrismCodeHighlighter("vbnet", showLineNumbers)
  override def verilog: PrismCodeHighlighter = PrismCodeHighlighter("verilog", showLineNumbers)
  override def vhdl: PrismCodeHighlighter = PrismCodeHighlighter("vhdl", showLineNumbers)
  override def vim: PrismCodeHighlighter = PrismCodeHighlighter("vim", showLineNumbers)
  override def wiki: PrismCodeHighlighter = PrismCodeHighlighter("wiki", showLineNumbers)
  override def xeora: PrismCodeHighlighter = PrismCodeHighlighter("xeora", showLineNumbers)
  override def xojo: PrismCodeHighlighter = PrismCodeHighlighter("xojo", showLineNumbers)
  override def yaml: PrismCodeHighlighter = PrismCodeHighlighter("yaml", showLineNumbers)
}

/* "normal" code highlighter */
object PrismCodeHighlighter {

  def apply(
      lang: String,
      showLineNumbers: Boolean,
      isMarkup: Boolean = false
  ): PrismCodeHighlighter = {
    val lineNums = if (showLineNumbers) Option(1) else None
    PrismCodeHighlighter(lang, lineNums, None, isMarkup)
  }
}

final case class PrismCodeHighlighter(
    lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    isMarkup: Boolean
) extends BaseCodeHighlighter(
      lang,
      lineNumbers,
      lineHighlight,
      None,
      isMarkup
    ) {

  def withLineNumsStart(startFrom: Int) =
    this.copy(lineNumbers = Option(startFrom))

  def withLineHighlight(lines: String, offset: Int = 0) =
    this.copy(lineHighlight = Option(lines -> offset))
}

/* command-line code highlighter */
object PrismCmdHighlighter {

  def apply(lang: String): PrismCmdHighlighter =
    PrismCmdHighlighter(
      lang,
      None,
      CommandLineOptions(None, Left("root" -> "localhost"))
    )
}

final case class PrismCmdHighlighter(
    lang: String,
    lineHighlight: Option[(String, Int)],
    commandLine: CommandLineOptions
) extends BaseCodeHighlighter(
      lang,
      None, // can't have line numbers when CMD
      lineHighlight,
      Option(commandLine),
      false
    ) {

  def withLineHighlight(lines: String, offset: Int = 0): PrismCmdHighlighter =
    this.copy(lineHighlight = Option(lines -> offset))

  def withUser(cmdUser: String, cmdHost: String = "localhost"): PrismCmdHighlighter =
    this.copy(commandLine = commandLine.copy(prefix = Left(cmdUser, cmdHost)))

  def withPrompt(cmdPrompt: String): PrismCmdHighlighter =
    this.copy(commandLine = commandLine.copy(prefix = Right(cmdPrompt)))

  def withOutputLines(cmdOutputLines: String): PrismCmdHighlighter =
    this.copy(
      commandLine = commandLine.copy(outputLines = Option(cmdOutputLines))
    )
}

object BaseCodeHighlighter {
  import PrismCodeHighlightComponents._
  import CodeSource._

  /* does ALL THE JOB :D */
  private def highlight(
      lang: String,
      lineNumbers: Option[Int],
      lineHighlight: Option[(String, Int)],
      commandLine: Option[CommandLineOptions],
      isMarkup: Boolean, // via unescaped-markup plugin
      codeSource: CodeSource
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
      cl.prefix.fold(
        {
          case (commandLineUser, commandLineHost) =>
            attrs += (data.user := commandLineUser)
            attrs += (data.host := commandLineHost)
        }, { commandLinePrompt =>
          attrs += (data.prompt := commandLinePrompt)
        }
      )
    }
    // line highlight or CMD output
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
    val languageClass = s"language-$lang"
    codeSource match {
      case PlainText(text) => {
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
      }
      case AJAX(url) => {
        attrs += (data.src := url)
        pre(cls := languageClass, classes.map(cls := _).toSeq, attrs.toSeq)
      }
      case JSONP(url, fileName) => {
        attrs += (data.jsonp := url)
        fileName.foreach { fn =>
          attrs += (data.filename := fn)
        }
        pre(cls := languageClass, classes.map(cls := _).toSeq, attrs.toSeq)
      }
    }
  }

  def highlightInline(lang: String, content: String): Frag =
    code(cls := s"language-$lang")(content)
}

abstract class BaseCodeHighlighter(
    lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    commandLine: Option[CommandLineOptions],
    isMarkup: Boolean
) extends CodeHighlighter {
  import BaseCodeHighlighter._
  import PrismCodeHighlightComponents._
  import CodeSource._

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

  /** Fetches a file from url via JSONP. Supported sites: Github, Gist, Bitbucket <br>
    *  Optionally provide gist fileName */
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

  /**
    * @param user Github user, e.g. "TheAdnan"
    * @param repo User's repo, e.g. "focustube"
    * @param filePath Path of the file, e.g. "bla/index.js"
    */
  def github(user: String, repo: String, filePath: String): Frag =
    jsonp(s"https://api.github.com/repos/$user/$repo/contents/$filePath")
}

final case class CommandLineOptions(
    outputLines: Option[String],
    prefix: Either[(String, String), String] // (user,host) or custom
)
