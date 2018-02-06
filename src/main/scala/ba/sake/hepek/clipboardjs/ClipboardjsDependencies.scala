package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html.structure.PageDependencies
import ba.sake.hepek.html.structure.Dependency

trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsVersion: String     = "1.7.1"
  def clipboardjsUseWebjars: Boolean = false

  def clipboardjsJSDependencies: List[String] =
    List(
      dependencyProvider.depPath(
        Dependency("clipboard.min.js", clipboardjsVersion, "clipboard.js")
      )
    )

  abstract override def scriptURLs =
    super.scriptURLs ++ clipboardjsJSDependencies

}
