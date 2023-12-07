package ba.sake.hepek.katex

import ba.sake.hepek.html.*

trait KatexDependencies extends PageDependencies {

  def katexSettings: KatexSettings = KatexSettings("0.10.2", "KaTeX")

  def katexDependencies =
    ComponentDependencies.default
      .withJsDependencies(
        Dependencies.default
          .withDeps(
            Dependency("katex.min.js", katexSettings.version, katexSettings.pkg),
            Dependency("contrib/auto-render.min.js", katexSettings.version, katexSettings.pkg)
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
        Dependencies.default
          .withDeps(Dependency("katex.min.css", katexSettings.version, katexSettings.pkg))
      )

  override def components =
    super.components.appended(katexSettings -> katexDependencies)
}
