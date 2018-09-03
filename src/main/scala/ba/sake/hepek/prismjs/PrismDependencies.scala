package ba.sake.hepek.prismjs

import ba.sake.hepek.html.structure._
import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.clipboardjs.ClipboardjsDependencies

trait PrismDependencies extends PageDependencies with ClipboardjsDependencies {
  import PrismCodeHighlightComponents._

  def prismSettings: PrismSettings = PrismSettings("1.15.0", "prism", DependencyProvider.cdnjs)

  def prismDependencies: ComponentDependencies = {
    val cssPluginDeps = (prismPlugins ++ optionalPluginDeps).filter(_._2).map {
      case (plugin, _) =>
        Dependency(s"plugins/$plugin/prism-$plugin.css", prismSettings.version, "prism")
    }

    val jsLangDeps = prismLanguageDeps.map { lang =>
      Dependency(s"components/prism-$lang.min.js", prismSettings.version, "prism")
    }
    val jsPluginDeps = (prismPlugins ++ optionalPluginDeps).map {
      case (plugin, _) =>
        Dependency(s"plugins/$plugin/prism-$plugin.min.js", prismSettings.version, "prism")
    }

    ComponentDependencies()
      .withCssDependencies(
        Dependencies().withDeps(
          Dependency(s"themes/${prismSettings.theme}.min.css",
                     prismSettings.version,
                     prismSettings.pkg) :: cssPluginDeps
        )
      )
      .withJsDependencies(
        Dependencies().withDeps(jsLangDeps ++ jsPluginDeps)
      )
  }

  override def components =
    super.components :+ (prismSettings, prismDependencies)

  // TODO keep-markup isn't working correctly... :/
  private def optionalPluginDeps: List[(String, Boolean)] =
    List(
      //if (prismKeepMarkup) Option("keep-markup" -> false) else None,
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

case class PrismSettings(
    override val version: String,
    override val pkg: String,
    override val depsProvider: DependencyProvider = DependencyProvider.cdnjs,
    /** FULL theme name, with "prism" prefix! See `Themes` */
    theme: String = Themes.Okaidia,
    showInvisibles: Boolean = false,
    showLanguage: Boolean = true,
    copyToClipboard: Boolean = true,
    //def prismKeepMarkup: Boolean = false // TODO
) extends BaseComponentSettings(version, pkg, depsProvider) {
  def withVersion(version: String)                       = copy(version = version)
  def withPkg(pkg: String)                               = copy(pkg = pkg)
  def withDepsProvider(depsProvider: DependencyProvider) = copy(depsProvider = depsProvider)
  def withTheme(theme: String)                           = copy(theme = theme)
  def withShowInvisibles(showInvisibles: Boolean)        = copy(showInvisibles = showInvisibles)
  def withShowLanguage(showLanguage: Boolean)            = copy(showLanguage = showLanguage)
  def withCopyToClipboard(copyToClipboard: Boolean)      = copy(copyToClipboard = copyToClipboard)
}
