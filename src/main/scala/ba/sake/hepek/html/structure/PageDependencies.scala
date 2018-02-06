package ba.sake.hepek.html.structure

trait PageDependencies {

  // CSS
  def styleURLs: List[String]    = List.empty
  def stylesInline: List[String] = List.empty

  // JS
  def scriptURLs: List[String]    = List.empty
  def scriptsInline: List[String] = List.empty

  def dependencyProvider: DependencyProvider = DependencyProvider.unpkg

}

case class Dependency(
    file: String,
    version: String,
    pkg: String,
    qParams: Option[String] = None
) {
  def queryParams: String = qParams.map(q => "?" + q).getOrElse("")
}

/* Dependency providers: CDNs, Webjars etc. */
object DependencyProvider {

  val unpkg: DependencyProvider = new DependencyProvider {
    override def depPath(dep: Dependency) =
      s"https://unpkg.com/${dep.pkg}@${dep.version}/dist/${dep.file}${dep.queryParams}"
  }
}

trait DependencyProvider {

  def depPath(dep: Dependency): String

}

case class WebjarsDependencyProvider(webjarsPath: String)
    extends DependencyProvider {
  override def depPath(dep: Dependency) =
    s"$webjarsPath/${dep.pkg}/${dep.file}${dep.queryParams}"
}
