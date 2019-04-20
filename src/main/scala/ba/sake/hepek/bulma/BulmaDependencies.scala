package ba.sake.hepek.bulma

import ba.sake.hepek.html.structure._

trait BulmaDependencies extends PageDependencies {

  val bulmaFilename: String = "bulma"

  def bulmaSettings: ComponentSettings =
    ComponentSettings("0.7.4", bulmaFilename, DependencyProvider.cdnjs)

  def bulmaDependencies = ComponentDependencies().withCssDependencies(
    Dependencies().withDeps(
      Dependency(s"css/$bulmaFilename.min.css", bulmaSettings.version, bulmaSettings.pkg)
    )
  )

  override def components = super.components :+ (bulmaSettings, bulmaDependencies)
}
