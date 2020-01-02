package ba.sake.hepek.html

import ba.sake.hepek.html.component.BasicComponents

trait StaticBundle extends Bundle { self: BasicComponents =>
  type StaticPage <: statik.StaticPage // override in impls

  /* handy aliases */
  type StaticSiteSettings = statik.StaticSiteSettings
  type BlogSettings       = statik.BlogSettings
  type Section            = statik.Section

  val StaticSiteSettings = statik.StaticSiteSettings
  val BlogSettings       = statik.BlogSettings
  val Section            = statik.Section
}
