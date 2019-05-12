package ba.sake.hepek

private[hepek] trait HepekAliases {

  val SiteSettings = html.SiteSettings
  type SiteSettings = html.SiteSettings

  val PageSettings = html.PageSettings
  type PageSettings = html.PageSettings

  // deps
  val Dependency = html.Dependency
  type Dependency = html.Dependency

  val Dependencies = html.Dependencies
  type Dependencies = html.Dependencies

  val DependencyProvider = html.DependencyProvider

  // grid
  val Ratio = html.component.GridComponents.Ratio
  type Ratio = html.component.GridComponents.Ratio

  val Ratios = html.component.GridComponents.Ratios
  type Ratios = html.component.GridComponents.Ratios

  val ScreenRatios = html.component.GridComponents.ScreenRatios
  type ScreenRatios = html.component.GridComponents.ScreenRatios
}
