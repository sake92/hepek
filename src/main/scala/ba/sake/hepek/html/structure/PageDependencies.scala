package ba.sake.hepek.html.structure

trait PageDependencies {

  // CSS
  def styleURLs: List[String]    = List.empty
  def stylesInline: List[String] = List.empty

  // JS
  def scriptURLs: List[String]    = List.empty
  def scriptsInline: List[String] = List.empty
}

case class Dependency(
    file: String,
    version: String,
    pkg: String,
    baseFolder: Option[String] = None, // usually "dist/", MUST end with slash!
    qParams: Option[String] = None
) {
  def queryParams: String = qParams.map(q => "?" + q).getOrElse("")
}

/* Dependency providers: CDNs, Webjars etc. */
object DependencyProvider {

  val unpkg: DependencyProvider = new DependencyProvider {
    override def depPath(dep: Dependency) = {
      val maybeBaseFolder = dep.baseFolder.getOrElse("")
      s"https://unpkg.com/${dep.pkg}@${dep.version}/${maybeBaseFolder}${dep.file}${dep.queryParams}"
    }
  }

  val cdnjs: DependencyProvider = new DependencyProvider {
    override def depPath(dep: Dependency) =
      s"https://cdnjs.cloudflare.com/ajax/libs/${dep.pkg}/${dep.version}/${dep.file}${dep.queryParams}"
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
