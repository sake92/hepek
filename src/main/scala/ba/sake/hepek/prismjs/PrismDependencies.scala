package ba.sake.hepek.prismjs

import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.jquery.JQueryDependencies
import ba.sake.hepek.clipboardjs.ClipboardjsDependencies
import ba.sake.hepek.html.structure.Dependency

trait PrismDependencies extends PageDependencies with ClipboardjsDependencies {
  import PrismCodeHighlightComponents._
  import CodeHighlightComponents._

  def prismVersion: String     = "1.10.0"
  def prismTheme: String       = "default"
  def prismUseWebjars: Boolean = false

  def prismCSSDependencies: List[String] = {
    val themeDeps = List(
      dependencyProvider.depPath(
        Dependency(s"themes/$prismTheme.min.css", prismVersion, "prism")
      )
    )
    val pluginDeps = prismPlugins.filter(_._2).map {
      case (plugin, _) =>
        dependencyProvider.depPath(
          Dependency(s"plugins/$plugin/prism-$plugin.css",
                     prismVersion,
                     "prism")
        )
    }
    themeDeps ++ pluginDeps
  }

  def prismJSDependencies: List[String] = {
    val langDeps = ("core" :: languageNames).map { lang =>
      dependencyProvider.depPath(
        Dependency(s"components/prism-$lang.min.js", prismVersion, "prism")
      )
    }
    val pluginDeps = prismPlugins.map {
      case (plugin, _) =>
        dependencyProvider.depPath(
          Dependency(s"plugins/$plugin/prism-$plugin.min.js",
                     prismVersion,
                     "prism")
        )
    }
    langDeps ++ pluginDeps
  }

  abstract override def styleURLs  = super.styleURLs ++ prismCSSDependencies
  abstract override def scriptURLs = super.scriptURLs ++ prismJSDependencies

}
