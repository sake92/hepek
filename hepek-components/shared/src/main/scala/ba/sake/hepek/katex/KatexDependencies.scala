package ba.sake.hepek.katex

import ba.sake.hepek.html.*

trait KatexDependencies extends PageDependencies {

  def katexSettings: KatexSettings =
    KatexSettings("0.16.21", "KaTeX").withDepsProvider(DependencyProvider.unpkg)

  // https://unpkg.com/katex@0.16.21/dist/katex.min.css
  def katexDependencies =
    ComponentDependencies.empty
      .withJsDependencies(
        Dependencies.empty
          .withDeps(
            Dependency("dist/katex.min.js", katexSettings.version, katexSettings.pkg),
            Dependency("dist/contrib/auto-render.min.js", katexSettings.version, katexSettings.pkg)
          )
          .withInlines(
            s"""
              // couldn't find better escape character, all other are used in Markdown
              renderMathInElement(
                document.body, {
                  delimiters: [
                    { left: "${katexSettings.delimitersInline._1}", right: "${katexSettings.delimitersInline._2}", display: false }, // inline
                    { left: "${katexSettings.delimitersBlock._1}", right: "${katexSettings.delimitersBlock._2}", display: true } // block, centered
                  ],
                  ignoredTags: [${katexSettings.ignoredTags.map("\"" + _ + "\"").mkString(",")}]
              });
            """
          )
      )
      .withCssDependencies(
        Dependencies.empty
          .withDeps(Dependency("dist/katex.min.css", katexSettings.version, katexSettings.pkg))
      )

  override def components =
    super.components.appended(katexSettings -> katexDependencies)
}
