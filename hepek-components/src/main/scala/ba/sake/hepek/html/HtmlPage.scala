package ba.sake.hepek.html

import scalatags.Text.all._
import ba.sake.stone.Wither
import ba.sake.hepek.html.pwa.WebAppManifest

trait HtmlPage extends PageDependencies {
  def siteSettings: SiteSettings = SiteSettings()

  def pageSettings: PageSettings = PageSettings()

  def metaSettings: MetaSettings =
    MetaSettings() // TODO fill all defaults
      .withOgTitle(pageSettings.title)

  def manifest: WebAppManifest = WebAppManifest(
    name = siteSettings.name.getOrElse(""),
    lang = Some(pageSettings.language),
    display = "minimal-ui", // upickle doesnt serialize default params... :(
    startUrl = "."
  )

  def contents: String = {
    // inline css
    val compStyleInlines = components.flatMap { _._2.cssDependencies.inlines }
    val allStyleInlines  = compStyleInlines ++ stylesInline
    // urls css
    val compStyleUrls = components.flatMap {
      case (cs, cd) =>
        cd.cssDependencies.urls ++
          cd.cssDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allStyleURLs = compStyleUrls ++ styleURLs

    // inline js
    val compScriptInlines = components.flatMap { _._2.jsDependencies.inlines }
    val allScriptInlines  = compScriptInlines ++ scriptsInline
    // urls js
    val compScriptUrls = components.flatMap {
      case (cs, cd) =>
        cd.jsDependencies.urls ++
          cd.jsDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allScriptURLs = compScriptUrls ++ scriptURLs

    // CONTENT
    "<!DOCTYPE html>" +
      html(lang := pageSettings.language)(
        head(
          headContent,
          allStyleURLs.map(u => link(rel := "stylesheet", href := u)) ++
            allStyleInlines.map(s => tag("style")(raw(s)))
        ),
        body(
          bodyContent,
          allScriptURLs.map(u => script(src := u)) ++
            allScriptInlines.map(s => script(raw(s)))
        )
      )
  }

  // <head>
  def headContent: Frag =
    frag(
      meta(charset := metaSettings.charset),
      meta(attr("http-equiv") := "X-UA-Compatible", content := metaSettings.xuaCompatible),
      meta(name := "viewport", content := metaSettings.viewport),
      meta(name := "generator", content := "hepek"),
      meta(name := "theme-color", content := metaSettings.themeColor),
      meta(name := "mobile-web-app-capable", content := "yes"),
      // page
      pageSettings.description.map(c => meta(name := "description", content := c)),
      metaSettings.first.map(c => meta(name := "first", content := c)),
      metaSettings.last.map(c => meta(name := "last", content := c)),
      metaSettings.prev.map(c => meta(name := "prev", content := c)),
      metaSettings.next.map(c => meta(name := "next", content := c)),
      metaSettings.editURI.map(c => meta(name := "EditURI", content := c)),
      metaSettings.subject.map(c => meta(name := "subject", content := c)),
      // geo
      metaSettings.geoICBM.map(c => meta(name := "ICBM", content := c)),
      metaSettings.geoPosition.map(c => meta(name := "geo.position", content := c)),
      metaSettings.geoRegion.map(c => meta(name := "geo.region", content := c)),
      metaSettings.geoPlacename.map(c => meta(name := "geo.placename", content := c)),
      metaSettings.me.map(c => meta(name := "me", content := c)),
      // google
      metaSettings.googleSiteVerification
        .map(c => meta(name := "google-site-verification", content := c)),
      siteSettings.googleAnalyticsTrackingId.map(
        id =>
          raw(
            s"""
          <!-- Global Site Tag (gtag.js) - Google Analytics -->
          <script async src="https://www.googletagmanager.com/gtag/js?id=$id"></script>
          <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() { dataLayer.push(arguments); }
            gtag('js', new Date());
            gtag('config', '$id');
          </script>
        """
          )
      ),
      // open graph
      metaSettings.ogUrl.map(c => meta(name := "og:url", content := c)),
      metaSettings.ogType.map(c => meta(name := "og:type", content := c)),
      metaSettings.ogTitle.map(c => meta(name := "og:title", content := c)),
      metaSettings.ogImage.map(c => meta(name := "og:image", content := c)),
      metaSettings.ogImageAlt.map(c => meta(name := "og:image:alt", content := c)),
      metaSettings.ogDescription.map(c => meta(name := "og:description", content := c)),
      metaSettings.ogSiteName.map(c => meta(name := "og:site_name", content := c)),
      metaSettings.ogLocale.map(c => meta(name := "og:locale", content := c)),
      metaSettings.articleAuthor.map(c => meta(name := "article:author", content := c)),
      // other
      tag("title")(
        pageSettings.title + siteSettings.name.map(n => " - " + n).getOrElse("")
      ),
      siteSettings.faviconNormal.map { fav =>
        link(rel := "shortcut icon", href := fav, tpe := "image/x-icon")
      }
    )

  // <body>
  def bodyContent: Frag = pageContent

  def pageContent: Frag = frag()
}

@Wither
final case class SiteSettings(
    name: Option[String] = None,
    faviconNormal: Option[String] = None,
    faviconInverted: Option[String] = None,
    googleAnalyticsTrackingId: Option[String] = None
)

@Wither
final case class PageSettings(
    title: String,
    label: String,
    language: String,
    description: Option[String]
) {

  def withTitle(t: String) =
    if (label.isEmpty || label == PageSettings.DefaultTitle)
      copy(title = t, label = t) // set label also, if not set
    else copy(title = t)
}

object PageSettings {
  val DefaultTitle    = "changeme"
  val DefaultLanguage = "en"

  def apply(title: String = DefaultTitle): PageSettings =
    PageSettings(title, title, DefaultLanguage, None)
}

@Wither
final case class MetaSettings(
    charset: String = "utf-8",
    xuaCompatible: String = "ie=edge",
    viewport: String = "width=device-width, initial-scale=1",
    themeColor: String = "#000",
    me: List[String] = Nil,
    // page
    subject: Option[String] = None,
    first: Option[String] = None,
    last: Option[String] = None,
    prev: Option[String] = None,
    next: Option[String] = None,
    editURI: Option[String] = None,
    // geo
    geoICBM: Option[String] = None,
    geoPosition: Option[String] = None,
    geoRegion: Option[String] = None,
    geoPlacename: Option[String] = None,
    // google
    googleSiteVerification: Option[String] = None,
    // open graph (fb, twitter)
    // note: twitter falls back to OG https://developer.twitter.com/en/docs/tweets/optimize-with-cards/guides/getting-started#twitter-cards-and-open-graph
    ogUrl: Option[String] = None,
    ogType: Option[String] = None,
    ogTitle: Option[String] = None,
    ogImage: Option[String] = None,
    ogImageAlt: Option[String] = None,
    ogDescription: Option[String] = None,
    ogSiteName: Option[String] = None,
    ogLocale: Option[String] = None,
    articleAuthor: Option[String] = None
)
