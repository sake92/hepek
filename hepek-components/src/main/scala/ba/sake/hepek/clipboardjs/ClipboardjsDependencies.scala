package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html._

trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsSettings: ComponentSettings =
    ComponentSettings("1.7.1", "clipboard.js", DependencyProvider.cdnjs)

  def clipboardjsDependencies: ComponentDependencies =
    ComponentDependencies().withJsDependencies(
      Dependencies().withDeps(
        Dependency("clipboard.min.js", clipboardjsSettings.version, clipboardjsSettings.pkg)
      )
    )

  override def components =
    super.components :+ (clipboardjsSettings, clipboardjsDependencies)
}
