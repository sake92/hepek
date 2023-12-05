package ba.sake.hepek.mathjax

import ba.sake.hepek.html._

trait MathjaxDependencies extends PageDependencies {
  def mathjaxConfig: String = "TeX-MML-AM_CHTML"

  def mathjaxSettings: ComponentSettings =
    ComponentSettings("2.7.2", "mathjax")

  def mathjaxDependencies = ComponentDependencies.default.withJsDependencies(
    Dependencies.default.withDeps(
      Dependency(
        "MathJax.js",
        mathjaxSettings.version,
        mathjaxSettings.pkg
      ).withQueryParams(s"config=$mathjaxConfig")
    )
  )

  override def components =
    super.components.appended(mathjaxSettings -> mathjaxDependencies)
}
