package fixtures.static

import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.plain.statik.PlainStaticBundle
import ba.sake.hepek.scalatags.all._

val Bundle = PlainStaticBundle()
import Bundle.*

object ids {
  val markdown = "md1"
}

trait SimpleStaticPage extends StaticPage {

  override def siteSettings =
    super.siteSettings
      .withName("Site name")
      .withFaviconNormal("site-favicon.png")

  override def pageSettings =
    super.pageSettings
      .withTitle("Page title")
      .withLabel("Page link label")
      .withDescription("Page description")
      .withLanguage("bs")

  override def metaSettings =
    super.metaSettings
      .withCharset("charset")
      .withXuaCompatible("xuaCompatible")
      .withViewport("viewport")
      .withThemeColor("themeColor")
      .withSubject("subject")
      .withFirst("first")
      .withLast("last")
      .withPrev("prev")
      .withNext("next")
      .withEditURI("editURI")
      .withGeoICBM("geoICBM")
      .withGeoPosition("geoPosition")
      .withGeoRegion("geoRegion")
      .withGeoPlacename("geoPlacename")
      .withOgUrl("ogUrl")
      .withOgType("ogType")
      .withOgTitle("ogTitle")
      .withOgImage("ogImage")
      .withOgImageAlt("ogImageAlt")
      .withOgDescription("ogDescription")
      .withOgSiteName("ogSiteName")
      .withOgLocale("ogLocale")
      .withArticleAuthor("articleAuthor")

  override def pageContent =
    frag(
      div(id := ids.markdown)(
        """
          # Header1
          ## Header2
          ### Header3
          #### Header4
          ##### Header5
          ###### Header6
        """.md
      )
    )
}
