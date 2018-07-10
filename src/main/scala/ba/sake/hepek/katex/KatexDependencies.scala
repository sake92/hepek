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

// https://en.wikipedia.org/wiki/Acute_accent
  // couldn't find better escape character, all other are used in Markdown
  def katexJsInlineDependencies: List[String] =
    List("""
        renderMathInElement(
          document.body, {
            delimiters: [
              { left: "´", right: "´", display: false }, // inline
              { left: "$$", right: "$$", display: true } // block, centered
            ]
        });
      """)

  def katexCssDependencies: List[String] =
    List(
      katexDepsProvider.depPath(Dependency("katex.min.css", katexVersion, pkg))
    )

  override def scriptURLs    = super.scriptURLs ++ katexJsDependencies
  override def scriptsInline = super.scriptsInline ++ katexJsInlineDependencies
  override def styleURLs     = super.styleURLs ++ katexCssDependencies

}
