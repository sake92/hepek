package ba.sake.hepek.html

/** Some libraries have its specific props. These are minimum they have to provide. */
trait BaseComponentSettings {
  def version: String
  def pkg: String
  def depsProvider: DependencyProvider
}

final case class ComponentSettings(
    version: String,
    pkg: String,
    depsProvider: DependencyProvider = DependencyProvider.cdnjs
) extends BaseComponentSettings {

  def withVersion(version: String): ComponentSettings =
    copy(version = version)

  def withPkg(pkg: String): ComponentSettings =
    copy(pkg = pkg)

  def withDepsProvider(depsProvider: DependencyProvider): ComponentSettings =
    copy(depsProvider = depsProvider)
}

final case class ComponentDependencies(
    cssDependencies: Dependencies = Dependencies(),
    jsDependencies: Dependencies = Dependencies()
) {

  def withCssDependencies(cssDependencies: Dependencies): ComponentDependencies =
    copy(cssDependencies = cssDependencies)

  def withJsDependencies(jsDependencies: Dependencies): ComponentDependencies =
    copy(jsDependencies = jsDependencies)
}
