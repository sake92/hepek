package ba.sake.hepek.katex

import ba.sake.hepek.html._

trait KatexDependencies extends PageDependencies {
  def katexSettings: KatexSettings = KatexSettings("0.10.2", "KaTeX")

  def katexDependencies =
    ComponentDependencies()
      .withJsDependencies(
        Dependencies()
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
        Dependencies()
          .withDeps(Dependency("katex.min.css", katexSettings.version, katexSettings.pkg))
      )

  override def components =
    super.components :+ (katexSettings, katexDependencies)
}

final case class KatexSettings(
    override val version: String,
    override val pkg: String,
    override val depsProvider: DependencyProvider = DependencyProvider.cdnjs,
    delimitersInline: (String, String) = ("´", "´"),
    delimitersBlock: (String, String) = ("$$", "$$"),
    ignoredTags: List[String] = List("script", "noscript", "style", "textarea", "pre", "code")
) extends BaseComponentSettings(version, pkg, depsProvider) {
  def withVersion(version: String)                       = copy(version = version)
  def withPkg(pkg: String)                               = copy(pkg = pkg)
  def withDepsProvider(depsProvider: DependencyProvider) = copy(depsProvider = depsProvider)

  def withDelimitersInline(delimitersInline: (String, String)) =
    copy(delimitersInline = delimitersInline)

  def withDelimitersBlock(delimitersBlock: (String, String)) =
    copy(delimitersBlock = delimitersBlock)
  def withIgnoredTags(ignoredTags: List[String]) = copy(ignoredTags = ignoredTags)
}
