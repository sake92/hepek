package ba.sake.hepek.html.structure

object SimpleStaticPage extends StaticPage {

  override def siteSettings =
    super.siteSettings
      .withName("Site name")
      .withFaviconNormal("site-favicon.png")

  override def pageSettings =
    super.pageSettings
      .withTitle("Page title")
      .withLabel("Page link label")
      .withDescription("Page description")
      .withCategory("Page category")
      .withLanguage("bs")
}
