package ba.sake.hepek

package object implicits {

  val SiteSettings = ba.sake.hepek.html.structure.SiteSettings
  type SiteSettings = ba.sake.hepek.html.structure.SiteSettings

  val Section = ba.sake.hepek.html.structure.blog.Section
  type Section = ba.sake.hepek.html.structure.blog.Section

  // grid
  val Ratio = ba.sake.hepek.html.component.GridComponents.Ratio
  type Ratio = ba.sake.hepek.html.component.GridComponents.Ratio

  val Ratios = ba.sake.hepek.html.component.GridComponents.Ratios
  type Ratios = ba.sake.hepek.html.component.GridComponents.Ratios

  val ScreenRatios = ba.sake.hepek.html.component.GridComponents.ScreenRatios
  type ScreenRatios = ba.sake.hepek.html.component.GridComponents.ScreenRatios

}
