package ba.sake.hepek.prismjs

import ba.sake.hepek.html.component.CodeHighlightComponents
import ba.sake.hepek.html.structure.PageDependencies

trait PrismDependencies extends PageDependencies {
  import PrismCodeHighlightComponents._
  import CodeHighlightComponents._

  def prismVersion: String = "1.10.0"

  def prismTheme: String = "prism-twilight"

  def prismCSSDependencies: List[String] = {
    val themeDeps = List(
      s"$cdnJSURL/prism/$prismVersion/themes/$prismTheme.min.css"
    )
    val pluginDeps = prismPlugins.filter(_._2).map {
      case (plugin, _) =>
        s"$cdnJSURL/prism/$prismVersion/plugins/$plugin/prism-$plugin.css"
    }
    themeDeps ++ pluginDeps
  }

  def prismJSDependencies: List[String] = {
    val langDeps = ("core" :: languageNames).map { lang =>
      s"$cdnJSURL/prism/$prismVersion/components/prism-$lang.min.js"
    }
    val pluginDeps = prismPlugins.map {
      case (plugin, _) =>
        s"$cdnJSURL/prism/$prismVersion/plugins/$plugin/prism-$plugin.min.js"
    }
    // clipboard js for copying a snippet
    val cpJS =
      s"$cdnJSURL/clipboard.js/1.7.1/clipboard.min.js"
    langDeps ++ List(cpJS) ++ pluginDeps
  }

  abstract override def styleURLs  = super.styleURLs ++ prismCSSDependencies
  abstract override def scriptURLs = super.scriptURLs ++ prismJSDependencies

}
