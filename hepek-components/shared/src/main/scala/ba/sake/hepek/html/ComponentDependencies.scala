package ba.sake.hepek.html

final class ComponentDependencies private (
    val cssDependencies: Dependencies,
    val jsDependencies: Dependencies
) {

  def withCssDependencies(cssDependencies: Dependencies): ComponentDependencies =
    copy(cssDependencies = cssDependencies)

  def withJsDependencies(jsDependencies: Dependencies): ComponentDependencies =
    copy(jsDependencies = jsDependencies)

  private def copy(
      cssDependencies: Dependencies = cssDependencies,
      jsDependencies: Dependencies = jsDependencies
  ) = new ComponentDependencies(cssDependencies, jsDependencies)
}

object ComponentDependencies:
  val default: ComponentDependencies =
    new ComponentDependencies(Dependencies.default, Dependencies.default)
