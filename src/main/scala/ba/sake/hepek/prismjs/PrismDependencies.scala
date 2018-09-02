package ba.sake.hepek.prismjs

import ba.sake.hepek.html.structure._
import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.clipboardjs.ClipboardjsDependencies

trait PrismDependencies extends PageDependencies with ClipboardjsDependencies {
  import PrismCodeHighlightComponents._

  def prismVersion: String                  = "1.15.0"
  def prismDepsProvider: DependencyProvider = DependencyProvider.cdnjs

  /** FULL theme name, with "prism" prefix! See `Themes` */
  def prismTheme: String            = Themes.Okaidia
  def prismShowInvisibles: Boolean  = false
  def prismShowLanguage: Boolean    = true
  def prismCopyToClipboard: Boolean = true
  //def prismKeepMarkup: Boolean = false

  def prismCSSDependencies: List[String] = {
    val themeDeps = List(
      prismDepsProvider.depPath(
        Dependency(s"themes/$prismTheme.min.css", prismVersion, "prism")
      )
    )
    val pluginDeps = (prismPlugins ++ optionalPluginDeps).filter(_._2).map {
      case (plugin, _) =>
        prismDepsProvider.depPath(
          Dependency(s"plugins/$plugin/prism-$plugin.css",
                     prismVersion,
                     "prism")
        )
    }
    themeDeps ++ pluginDeps
  }

  def prismJSDependencies: List[String] = {
    val langDeps = prismLanguageDeps.map { lang =>
      prismDepsProvider.depPath(
        Dependency(s"components/prism-$lang.min.js", prismVersion, "prism")
      )
    }
    val pluginDeps = (prismPlugins ++ optionalPluginDeps).map {
      case (plugin, _) =>
        prismDepsProvider.depPath(
          Dependency(s"plugins/$plugin/prism-$plugin.min.js",
                     prismVersion,
                     "prism")
        )
    }
    langDeps ++ pluginDeps
  }

  override def styleURLs  = super.styleURLs ++ prismCSSDependencies
  override def scriptURLs = super.scriptURLs ++ prismJSDependencies

  // TODO keep-markup isn't working correctly... :/
  private def optionalPluginDeps: List[(String, Boolean)] =
    List(
      //if (prismKeepMarkup) Option("keep-markup" -> false) else None,
      if (prismShowInvisibles) Option("show-invisibles"    -> true) else None,
      if (prismShowLanguage) Option("show-language"        -> false) else None,
      if (prismCopyToClipboard) Option("copy-to-clipboard" -> false) else None
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
