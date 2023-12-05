package ba.sake.hepek.html

/** Some libraries have its specific props. These are minimum they have to provide. */
trait BaseComponentSettings {
  def version: String
  def pkg: String
  def depsProvider: DependencyProvider
}

final class ComponentSettings private (
    val version: String,
    val pkg: String,
    val depsProvider: DependencyProvider
) extends BaseComponentSettings {

  def withVersion(version: String): ComponentSettings =
    copy(version = version)

  def withPkg(pkg: String): ComponentSettings =
    copy(pkg = pkg)

  def withDepsProvider(depsProvider: DependencyProvider): ComponentSettings =
    copy(depsProvider = depsProvider)

  private def copy(
      version: String = version,
      pkg: String = pkg,
      depsProvider: DependencyProvider = depsProvider
  ) = new ComponentSettings(version, pkg, depsProvider)
}

object ComponentSettings:
  def apply(version: String, pkg: String): ComponentSettings =
    new ComponentSettings(version, pkg, DependencyProvider.cdnjs)
