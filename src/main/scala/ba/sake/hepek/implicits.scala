package ba.sake.hepek

package object implicits {

  val SiteSettings = ba.sake.hepek.html.structure.SiteSettings
  type SiteSettings = ba.sake.hepek.html.structure.SiteSettings

  val PageSettings = ba.sake.hepek.html.structure.PageSettings
  type PageSettings = ba.sake.hepek.html.structure.PageSettings

  val BlogSettings = ba.sake.hepek.html.structure.blog.BlogSettings
  type BlogSettings = ba.sake.hepek.html.structure.blog.BlogSettings

  val Section = ba.sake.hepek.html.structure.blog.Section
  type Section = ba.sake.hepek.html.structure.blog.Section

  // deps
  val Dependency = ba.sake.hepek.html.structure.Dependency
  type Dependency = ba.sake.hepek.html.structure.Dependency

  val Dependencies = ba.sake.hepek.html.structure.Dependencies
  type Dependencies = ba.sake.hepek.html.structure.Dependencies

  val DependencyProvider = ba.sake.hepek.html.structure.DependencyProvider

  // grid
  val Ratio = ba.sake.hepek.html.component.GridComponents.Ratio
  type Ratio = ba.sake.hepek.html.component.GridComponents.Ratio

  val Ratios = ba.sake.hepek.html.component.GridComponents.Ratios
  type Ratios = ba.sake.hepek.html.component.GridComponents.Ratios

  val ScreenRatios = ba.sake.hepek.html.component.GridComponents.ScreenRatios
  type ScreenRatios = ba.sake.hepek.html.component.GridComponents.ScreenRatios

}
