package ba.sake.hepek.html
import ba.sake.stone.Wither

/** Some libraries have its specific props. These are minimum they have to provide. */
trait BaseComponentSettings {
  def version: String
  def pkg: String
  def depsProvider: DependencyProvider
}

@Wither
final case class ComponentSettings(
    version: String,
    pkg: String,
    depsProvider: DependencyProvider = DependencyProvider.cdnjs
) extends BaseComponentSettings

@Wither
final case class ComponentDependencies(
    cssDependencies: Dependencies = Dependencies(),
    jsDependencies: Dependencies = Dependencies()
)
