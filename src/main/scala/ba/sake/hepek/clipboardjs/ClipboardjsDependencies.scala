package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html.structure._

trait ClipboardjsDependencies extends PageDependencies {

  def clipboardjsVersion: String = "1.7.1"

  def clipboardjsJSDependencies: List[String] =
    List(
      clipboardjsDepsProvider.depPath(
        Dependency("clipboard.min.js", clipboardjsVersion, "clipboard.js")
      )
    )

  def clipboardjsDepsProvider = DependencyProvider.cdnjs

  override def scriptURLs =
    super.scriptURLs ++ clipboardjsJSDependencies

}
