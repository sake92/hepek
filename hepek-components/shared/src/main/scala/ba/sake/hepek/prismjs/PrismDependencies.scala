package ba.sake.hepek.prismjs

import ba.sake.hepek.clipboardjs.ClipboardjsDependencies
import ba.sake.hepek.html._

private[hepek] trait PrismDependencies extends ClipboardjsDependencies {

  def prismSettings: PrismSettings = PrismSettings("1.29.0", "prism")

  def prismDependencies: ComponentDependencies = {
    val cssPluginDeps =
      (prismSettings.plugins ++ optionalPluginDeps).filter(_._2).map { case (plugin, _) =>
        Dependency(s"plugins/$plugin/prism-$plugin.css", prismSettings.version, "prism")
      }
    val jsLangDeps = prismSettings.languages.map { lang =>
      Dependency(s"components/prism-$lang.min.js", prismSettings.version, "prism")
    }
    val jsPluginDeps = (prismSettings.plugins ++ optionalPluginDeps).map { case (plugin, _) =>
      Dependency(s"plugins/$plugin/prism-$plugin.min.js", prismSettings.version, "prism")
    }

    ComponentDependencies.default
      .withCssDependencies(
        Dependencies.default.withDeps(
          cssPluginDeps.prepended(
            Dependency(
              s"themes/${prismSettings.theme}.min.css",
              prismSettings.version,
              prismSettings.pkg
            )
          )
        )
      )
      .withJsDependencies(
        Dependencies.default.withDeps(jsLangDeps ++ jsPluginDeps)
      )
  }

  override def components =
    super.components.appended(prismSettings -> prismDependencies)

  private def optionalPluginDeps: List[(String, Boolean)] =
    List(
      Option.when(prismSettings.keepMarkup)("keep-markup"            -> false),
      Option.when(prismSettings.showInvisibles)("show-invisibles"    -> true),
      Option.when(prismSettings.showLanguage)("show-language"        -> false),
      Option.when(prismSettings.copyToClipboard)("copy-to-clipboard" -> false)
    ).flatten
}
