package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html.structure._

/** Prismjs needs this for copying operation */
trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsSettings: ComponentSettings =
    ComponentSettings("1.7.1", "clipboard.js", DependencyProvider.cdnjs)

  def clipboardjsDependencies = ComponentDependencies().withDependencies(
    Dependency("clipboard.min.js",
               clipboardjsSettings.version,
               clipboardjsSettings.pkg)
  )

  override def componentsDeps = super.componentsDeps :+ clipboardjsDependencies
}
