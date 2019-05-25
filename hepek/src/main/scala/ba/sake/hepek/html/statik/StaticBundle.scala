package ba.sake.hepek.html

trait StaticBundle extends Bundle {

  type StaticPage <: statik.StaticPage

  val StaticSiteSettings = statik.StaticSiteSettings
  type StaticSiteSettings = statik.StaticSiteSettings

  val BlogSettings = statik.BlogSettings
  type BlogSettings = statik.BlogSettings

  val Section = statik.Section
  type Section = statik.Section
}
