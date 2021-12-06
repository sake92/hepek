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
    new ComponentSettings(version = version, pkg = pkg, depsProvider = depsProvider)

  def withPkg(pkg: String): ComponentSettings =
    new ComponentSettings(version = version, pkg = pkg, depsProvider = depsProvider)

  def withDepsProvider(depsProvider: DependencyProvider): ComponentSettings =
    new ComponentSettings(version = version, pkg = pkg, depsProvider = depsProvider)
}


final case class ComponentDependencies(
    cssDependencies: Dependencies = Dependencies(),
    jsDependencies: Dependencies = Dependencies()
) {

  def withCssDependencies(cssDependencies: Dependencies): ComponentDependencies =
    new ComponentDependencies(cssDependencies = cssDependencies, jsDependencies = jsDependencies)

  def withJsDependencies(jsDependencies: Dependencies): ComponentDependencies =
    new ComponentDependencies(cssDependencies = cssDependencies, jsDependencies = jsDependencies)
}
