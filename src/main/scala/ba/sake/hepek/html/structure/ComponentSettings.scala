package ba.sake.hepek.html.structure

abstract class BaseComponentSettings(
    val version: String,
    val pkg: String,
    val depsProvider: DependencyProvider
) {
  def withVersion(version: String)
  def withPkg(pkg: String)
  def withDepsProvider(depsProvider: DependencyProvider)
}

case class ComponentSettings(
    override val version: String,
    override val pkg: String,
    override val depsProvider: DependencyProvider = DependencyProvider.cdnjs
) extends BaseComponentSettings(version, pkg, depsProvider) {
  def withVersion(version: String)                       = copy(version = version)
  def withPkg(pkg: String)                               = copy(pkg = pkg)
  def withDepsProvider(depsProvider: DependencyProvider) = copy(depsProvider = depsProvider)
}

case class ComponentDependencies(
    cssDependencies: Dependencies = Dependencies(),
    jsDependencies: Dependencies = Dependencies()
) {
  def withCssDependencies(cssDependencies: Dependencies) = copy(cssDependencies = cssDependencies)
  def withJsDependencies(jsDependencies: Dependencies)   = copy(jsDependencies = jsDependencies)
}
