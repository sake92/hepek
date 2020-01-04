package ba.sake.hepek.html

import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.html.component.ComponentsBundle

trait StaticBundle extends Bundle {
  type StaticPage <: statik.StaticPage

  val StaticSiteSettings = statik.StaticSiteSettings
  val BlogSettings       = statik.BlogSettings
  val Section            = statik.Section
}
