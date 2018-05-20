package ba.sake.hepek.katex

import ba.sake.hepek.html.structure._

trait KatexDependencies extends PageDependencies {

  def katexVersion: String                  = "0.9.0"
  def katexDepsProvider: DependencyProvider = DependencyProvider.cdnjs

  private val pkg = "KaTeX"

  def katexJsDependencies: List[String] =
    List(
      katexDepsProvider.depPath(Dependency("katex.min.js", katexVersion, pkg)),
      katexDepsProvider.depPath(
        Dependency("contrib/auto-render.min.js", katexVersion, pkg)
      )
    )

  def katexCssDependencies: List[String] =
    List(
      katexDepsProvider.depPath(
        Dependency("katex.min.css", katexVersion, pkg)
      )
    )

  override def scriptURLs = super.scriptURLs ++ katexJsDependencies
  override def styleURLs  = super.styleURLs ++ katexCssDependencies

}
