package ba.sake.hepek.katex

import ba.sake.hepek.html.structure._

trait KatexDependencies extends PageDependencies {

  def katexSettings: ComponentSettings =
    ComponentSettings("0.9.0", "KaTeX", DependencyProvider.cdnjs)

  def katexDependencies =
    ComponentDependencies()
      .withJsDependencies(
        Dependencies()
          .withDeps(
            Dependency("katex.min.js", katexSettings.version, katexSettings.pkg),
            Dependency("contrib/auto-render.min.js", katexSettings.version, katexSettings.pkg)
          )
          .withInlines(
            """
              // couldn't find better escape character, all other are used in Markdown
              renderMathInElement(
                document.body, {
                  delimiters: [
                    { left: "´", right: "´", display: false }, // inline
                    { left: "$$", right: "$$", display: true } // block, centered
                  ]
              });
            """
          )
      )
      .withCssDependencies(
        Dependencies()
          .withDeps(Dependency("katex.min.css", katexSettings.version, katexSettings.pkg))
      )

  override def components =
    super.components :+ (katexSettings, katexDependencies)
}
