package ba.sake.hepek.html.structure

import ba.sake.hepek.html.statik.StaticPage

object SimpleStaticPage extends StaticPage {

  override def siteSettings =
    super.siteSettings
      .withName("Site name")
      .withFaviconNormal("site-favicon.png")

  override def pageCategory: Option[String] = Some("Page category")

  override def pageSettings =
    super.pageSettings
      .withTitle("Page title")
      .withLabel("Page link label")
      .withDescription("Page description")
      .withLanguage("bs")
}
