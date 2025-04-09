package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html.*

trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsSettings: ComponentSettings =
    ComponentSettings("1.7.1", "clipboard.js")

  def clipboardjsDependencies: ComponentDependencies =
    ComponentDependencies.empty.withJsDependencies(
      Dependencies.empty.withDeps(
        Dependency("clipboard.min.js", clipboardjsSettings.version, clipboardjsSettings.pkg)
      )
    )

  override def components =
    super.components.appended(clipboardjsSettings -> clipboardjsDependencies)
}
