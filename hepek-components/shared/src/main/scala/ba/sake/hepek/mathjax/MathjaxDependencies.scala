package ba.sake.hepek.mathjax

import ba.sake.hepek.html._

trait MathjaxDependencies extends PageDependencies {
  def mathjaxConfig: String = "TeX-MML-AM_CHTML"

  def mathjaxSettings: ComponentSettings =
    ComponentSettings("2.7.2", "mathjax", DependencyProvider.cdnjs)

  def mathjaxDependencies = ComponentDependencies().withJsDependencies(
    Dependencies().withDeps(
      Dependency(
        "MathJax.js",
        mathjaxSettings.version,
        mathjaxSettings.pkg,
        qParams = Option(s"config=$mathjaxConfig")
      )
    )
  )

  override def components =
    super.components.appended(mathjaxSettings -> mathjaxDependencies)
}
