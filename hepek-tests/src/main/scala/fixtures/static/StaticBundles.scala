package fixtures.static

import scalatags.Text.all._
import ba.sake.hepek.bulma.statik.BulmaStaticBundle
import ba.sake.hepek.html.StaticBundle

import ba.sake.hepek.html.component.MarkdownComponents
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.plain.statik.PlainStaticBundle
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.w3css.statik.W3CssStaticBundle
import ba.sake.hepek.html.MetaSettings
import scalatags.text.Builder

object StaticPages {
  val all: List[SimpleStaticPage] = List(Plain, Bootstrap3, Bulma, W3Css)
}

object Plain extends SimpleStaticPage {
  val bundle: PlainStaticBundle = PlainStaticBundle()
}

object Bootstrap3 extends SimpleStaticPage {
  val bundle: BootstrapStaticBundle = BootstrapStaticBundle()
}

object Bulma extends SimpleStaticPage {
  val bundle: BulmaStaticBundle = BulmaStaticBundle()
}

object W3Css extends SimpleStaticPage {
  val bundle: W3CssStaticBundle = W3CssStaticBundle()
}

object ids {
  val md = "md1"
}

trait SimpleStaticPage extends StaticPage {
  val bundle: StaticBundle with MarkdownComponents
  import bundle._

  override def siteSettings: SiteSettings =
    super.siteSettings
      .withName("Site name")
      .withFaviconNormal("site-favicon.png")
      .withGoogleAnalyticsTrackingId("g-123")

  override def pageSettings: PageSettings =
    super.pageSettings
      .withTitle("Page title")
      .withLabel("Page link label")
      .withDescription("Page description")
      .withLanguage("bs")

  override def metaSettings: MetaSettings =
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
      .withGoogleSiteVerification("googleSiteVerification")
      .withOgUrl("ogUrl")
      .withOgType("ogType")
      .withOgTitle("ogTitle")
      .withOgImage("ogImage")
      .withOgImageAlt("ogImageAlt")
      .withOgDescription("ogDescription")
      .withOgSiteName("ogSiteName")
      .withOgLocale("ogLocale")
      .withArticleAuthor("articleAuthor")

  override def pageContent: Frag[Builder,String] =
    frag(
      div(id := ids.md)(
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
