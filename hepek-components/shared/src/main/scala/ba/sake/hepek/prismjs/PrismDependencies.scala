package ba.sake.hepek.prismjs

import ba.sake.hepek.clipboardjs.ClipboardjsDependencies
import ba.sake.hepek.html.*

trait PrismDependencies extends ClipboardjsDependencies {

  def prismSettings: PrismSettings = PrismSettings("1.30.0", "prism")

  def prismDependencies: ComponentDependencies = {
    val cssPluginDeps =
      prismSettings.plugins.filter(_._2).map { case (plugin, _) =>
        Dependency(s"plugins/$plugin/prism-$plugin.css", prismSettings.version, "prism")
      }
    val jsDeps = List(
        Dependency(
          s"prism.js",
          prismSettings.version,
          prismSettings.pkg
        )
      )
    val jsPluginDeps = prismSettings.plugins.map { case (plugin, _) =>
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
        Dependencies.default.withDeps(jsDeps ++ jsPluginDeps)
      )
  }

  override def components =
    super.components.appended(prismSettings -> prismDependencies)

}
