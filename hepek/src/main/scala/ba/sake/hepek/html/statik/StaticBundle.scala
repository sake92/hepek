package ba.sake.hepek.html

trait StaticBundle extends Bundle {
  type StaticPage <: statik.StaticPage

  val StaticSiteSettings = statik.StaticSiteSettings
  val BlogSettings       = statik.BlogSettings
  val Section            = statik.Section
}
