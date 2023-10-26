package ba.sake.hepek.prismjs

import ba.sake.hepek.clipboardjs.ClipboardjsDependencies
import ba.sake.hepek.html._


trait PrismDependencies extends ClipboardjsDependencies {
  def prismSettings: PrismSettings = PrismSettings("1.29.0", "prism", DependencyProvider.cdnjs)

  def prismDependencies: ComponentDependencies = {
    val cssPluginDeps =
      (prismSettings.plugins ++ optionalPluginDeps).filter(_._2).map {
        case (plugin, _) =>
          Dependency(s"plugins/$plugin/prism-$plugin.css", prismSettings.version, "prism")
      }
    val jsLangDeps = prismSettings.languages.map { lang =>
      Dependency(s"components/prism-$lang.min.js", prismSettings.version, "prism")
    }
    val jsPluginDeps = (prismSettings.plugins ++ optionalPluginDeps).map {
      case (plugin, _) =>
        Dependency(s"plugins/$plugin/prism-$plugin.min.js", prismSettings.version, "prism")
    }

    ComponentDependencies()
      .withCssDependencies(
        Dependencies().withDeps(
          Dependency(
            s"themes/${prismSettings.theme}.min.css",
            prismSettings.version,
            prismSettings.pkg
          ) :: cssPluginDeps
        )
      )
      .withJsDependencies(
        Dependencies().withDeps(jsLangDeps ++ jsPluginDeps)
      )
  }

  override def components =
    super.components :+ (prismSettings, prismDependencies)

  private def optionalPluginDeps: List[(String, Boolean)] =
    List(
      if (prismSettings.keepMarkup) Option("keep-markup"            -> false) else None,
      if (prismSettings.showInvisibles) Option("show-invisibles"    -> true) else None,
      if (prismSettings.showLanguage) Option("show-language"        -> false) else None,
      if (prismSettings.copyToClipboard) Option("copy-to-clipboard" -> false) else None
    ).flatten
}

object Themes {
  val Default        = "prism"
  val Coy            = "prism-coy"
  val Dark           = "prism-dark"
  val Funky          = "prism-funky"
  val SolarizedLight = "prism-solarizedlight"
  val Okaidia        = "prism-okaidia"
  val Tomorrow       = "prism-tomorrow"
  val Twilight       = "prism-twilight"
}


final case class PrismSettings(
    version: String,
    pkg: String,
    depsProvider: DependencyProvider = DependencyProvider.cdnjs,
    theme: String = Themes.Okaidia,
    languages: List[String] = PrismConsts.languages,
    plugins: List[(String, Boolean)] = PrismConsts.plugins,
    showInvisibles: Boolean = false,
    showLanguage: Boolean = true,
    copyToClipboard: Boolean = true,
    keepMarkup: Boolean = true
) extends BaseComponentSettings {

  def withVersion(version: String): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withPkg(pkg: String): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withDepsProvider(depsProvider: DependencyProvider): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withTheme(theme: String): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withLanguages(languages: List[String]): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withLanguages(languages: String*): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup,
      languages = languages.toList
    )

  def withPlugins(plugins: List[(String, Boolean)]): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withPlugins(plugins: (String, Boolean)*): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup,
      plugins = plugins.toList
    )

  def withShowInvisibles(showInvisibles: Boolean): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withShowLanguage(showLanguage: Boolean): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withCopyToClipboard(copyToClipboard: Boolean): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )

  def withKeepMarkup(keepMarkup: Boolean): PrismSettings =
    new PrismSettings(
      version = version,
      pkg = pkg,
      depsProvider = depsProvider,
      theme = theme,
      languages = languages,
      plugins = plugins,
      showInvisibles = showInvisibles,
      showLanguage = showLanguage,
      copyToClipboard = copyToClipboard,
      keepMarkup = keepMarkup
    )
}

object PrismConsts {

  val languages: List[String] =
    List(
      "core",
      "abap",
      "ada",
      "apacheconf",
      "apl",
      "applescript",
      "asciidoc",
      "asm6502",
      "autohotkey",
      "autoit",
      "bash",
      "basic",
      "batch",
      "brainfuck",
      "bro",
      "clike",
      "c",     // extends clike
      "bison", // extends c
      "cpp",
      "arduino", // extends c++
      "csharp",
      "css",
      "css-extras",
      "csp",
      "d",
      "dart",
      "diff",
      "docker",
      "eiffel",
      "elixir",
      "elm",
      "erlang",
      "fortran",
      "fsharp",
      "gherkin",
      "git",
      "glsl",
      "go",
      "graphql",
      "groovy",
      "haml",
      "haskell",
      "haxe",
      "http",
      "hpkp",
      "hsts",
      "ichigojam",
      "icon",
      "inform7",
      "ini",
      "io",
      "j",
      "java",
      "javascript",
      "actionscript", // extends js
      "coffeescript", // extends js
      "flow",
      "jolie",
      "json",
      "julia",
      "keyman",
      "kotlin",
      "latex",
      "less",
      "livescript",
      "lolcode",
      "lua",
      "makefile",
      "markup",
      "markup-templating",
      "aspnet", // extends markup
      //"django", // extends markup, TODO throws error...?
      "handlebars", // extends markup
      "jsx",        // extends markup
      "markdown",   // extends markup
      "matlab",
      "mel",
      "mizar",
      "monkey",
      "n4js",
      "nasm",
      "nginx",
      "nim",
      "nix",
      "nsis",
      "objectivec",
      "ocaml",
      "opencl",
      "oz",
      "parigp",
      "parser",
      "pascal",
      "perl",
      "php",
      "php-extras",
      "powershell",
      "processing",
      "prolog",
      "properties",
      "protobuf",
      "pug",
      "puppet",
      "pure",
      "python",
      "q",
      "qore",
      "r",
      "reason",
      "renpy",
      "rest",
      "rip",
      "roboconf",
      "ruby",
      "crystal", // extends ruby
      "rust",
      "sas",
      "sass",
      "scala",
      "scheme",
      "scss",
      "smalltalk",
      "smarty",
      "sql",
      "stylus",
      "swift",
      "tcl",
      "textile",
      "tsx",
      "twig",
      "typescript",
      "vbnet",
      "verilog",
      "vhdl",
      "vim",
      "wiki",
      "xeora",
      "xojo",
      "yaml"
    )

  // not every plugin has CSS, so tuples (pluginName, hasCSS)
  // also see optionalPluginDeps in PrismDependencies
  val plugins: List[(String, Boolean)] = List(
    "autolinker"           -> true,  // auto create links
    "command-line"         -> true,  // cmd with nice prompt, etc...
    "data-uri-highlight"   -> false, // highliht url() stuff inside of CSS
    "file-highlight"       -> false, // downloads file via AJAX
    "jsonp-highlight"      -> false, // Gist, Github... via JSONP
    "line-highlight"       -> true,  // highlight SPECIFIC lines 1-5,9 ...
    "line-numbers"         -> true,  // line NUMBERS on the left
    "normalize-whitespace" -> false, // auto "trim" leading whitespace
    "previewers"           -> true,  // preview CSS stuff live (colors, gradients..)
    "toolbar"              -> true,  // needed for copy plugin and show-language etc
    "unescaped-markup"     -> true,  // handy for HTML markup
    "wpd"                  -> true   // WebPlatform Docs
  )
}
