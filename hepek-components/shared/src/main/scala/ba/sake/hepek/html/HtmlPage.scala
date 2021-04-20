package ba.sake.hepek

package html

import ba.sake.hepek.html.pwa.WebAppManifest
import ba.sake.hepek.scalatags.all.{html => htmlTag, _}
import ba.sake.kalem.Wither

// TODO move to SSG
// not much of a benefit in ScalaJS world?
trait HtmlPage extends PageDependencies {
  def siteSettings: SiteSettings = SiteSettings()

  def pageSettings: PageSettings = PageSettings()

  def metaSettings: MetaSettings =
    MetaSettings() // TODO fill all defaults
      .withOgTitle(siteSettings.name)

  def manifest: WebAppManifest = WebAppManifest(
    name = siteSettings.name.getOrElse(""),
    lang = Some(pageSettings.language)
  )

  def contents: String = {
    // css
    val compStyleInlines = components.flatMap { _._2.cssDependencies.inlines }
    val allStyleInlines  = compStyleInlines ++ stylesInline
    val compStyleUrls = components.flatMap {
      case (cs, cd) =>
        cd.cssDependencies.urls ++
          cd.cssDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allStyleURLs = compStyleUrls ++ styleURLs

    // js
    val compScriptInlines = components.flatMap { _._2.jsDependencies.inlines }
    val allScriptInlines  = compScriptInlines ++ scriptsInline
    val compScriptUrls = components.flatMap {
      case (cs, cd) =>
        cd.jsDependencies.urls ++
          cd.jsDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allScriptURLs = compScriptUrls ++ scriptURLs

    "<!DOCTYPE html>" +
      htmlTag(lang := pageSettings.language)(
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
  def headContent: Frag = frag(
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
    // google
    metaSettings.googleSiteVerification
      .map(c => meta(name := "google-site-verification", content := c)),
    siteSettings.googleAnalyticsTrackingId
      .map(
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
      link(rel := "shortcut icon", href := fav, tpe := guessMimeType(fav))
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
) {

  def withName(name: Option[String]): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId
    )

  def withName(name: String): SiteSettings =
    new SiteSettings(
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId,
      name = Option(name)
    )

  def withFaviconNormal(faviconNormal: Option[String]): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId
    )

  def withFaviconNormal(faviconNormal: String): SiteSettings =
    new SiteSettings(
      name = name,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId,
      faviconNormal = Option(faviconNormal)
    )

  def withFaviconInverted(faviconInverted: Option[String]): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId
    )

  def withFaviconInverted(faviconInverted: String): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId,
      faviconInverted = Option(faviconInverted)
    )

  def withGoogleAnalyticsTrackingId(googleAnalyticsTrackingId: Option[String]): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = googleAnalyticsTrackingId
    )

  def withGoogleAnalyticsTrackingId(googleAnalyticsTrackingId: String): SiteSettings =
    new SiteSettings(
      name = name,
      faviconNormal = faviconNormal,
      faviconInverted = faviconInverted,
      googleAnalyticsTrackingId = Option(googleAnalyticsTrackingId)
    )
}

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

  def withLabel(label: String): PageSettings =
    new PageSettings(title = title, label = label, language = language, description = description)

  def withLanguage(language: String): PageSettings =
    new PageSettings(title = title, label = label, language = language, description = description)

  def withDescription(description: Option[String]): PageSettings =
    new PageSettings(title = title, label = label, language = language, description = description)

  def withDescription(description: String): PageSettings =
    new PageSettings(
      title = title,
      label = label,
      language = language,
      description = Option(description)
    )
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
) {

  def withCharset(charset: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withXuaCompatible(xuaCompatible: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withViewport(viewport: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withThemeColor(themeColor: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withSubject(subject: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withSubject(subject: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      subject = Option(subject)
    )

  def withFirst(first: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withFirst(first: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      first = Option(first)
    )

  def withLast(last: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withLast(last: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      last = Option(last)
    )

  def withPrev(prev: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withPrev(prev: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      prev = Option(prev)
    )

  def withNext(next: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withNext(next: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      next = Option(next)
    )

  def withEditURI(editURI: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withEditURI(editURI: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      editURI = Option(editURI)
    )

  def withGeoICBM(geoICBM: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withGeoICBM(geoICBM: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      geoICBM = Option(geoICBM)
    )

  def withGeoPosition(geoPosition: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withGeoPosition(geoPosition: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      geoPosition = Option(geoPosition)
    )

  def withGeoRegion(geoRegion: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withGeoRegion(geoRegion: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      geoRegion = Option(geoRegion)
    )

  def withGeoPlacename(geoPlacename: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withGeoPlacename(geoPlacename: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      geoPlacename = Option(geoPlacename)
    )

  def withGoogleSiteVerification(googleSiteVerification: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withGoogleSiteVerification(googleSiteVerification: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      googleSiteVerification = Option(googleSiteVerification)
    )

  def withOgUrl(ogUrl: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgUrl(ogUrl: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogUrl = Option(ogUrl)
    )

  def withOgType(ogType: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgType(ogType: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogType = Option(ogType)
    )

  def withOgTitle(ogTitle: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgTitle(ogTitle: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogTitle = Option(ogTitle)
    )

  def withOgImage(ogImage: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgImage(ogImage: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogImage = Option(ogImage)
    )

  def withOgImageAlt(ogImageAlt: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgImageAlt(ogImageAlt: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogImageAlt = Option(ogImageAlt)
    )

  def withOgDescription(ogDescription: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgDescription(ogDescription: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogDescription = Option(ogDescription)
    )

  def withOgSiteName(ogSiteName: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgSiteName(ogSiteName: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor,
      ogSiteName = Option(ogSiteName)
    )

  def withOgLocale(ogLocale: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withOgLocale(ogLocale: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      articleAuthor = articleAuthor,
      ogLocale = Option(ogLocale)
    )

  def withArticleAuthor(articleAuthor: Option[String]): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = articleAuthor
    )

  def withArticleAuthor(articleAuthor: String): MetaSettings =
    new MetaSettings(
      charset = charset,
      xuaCompatible = xuaCompatible,
      viewport = viewport,
      themeColor = themeColor,
      subject = subject,
      first = first,
      last = last,
      prev = prev,
      next = next,
      editURI = editURI,
      geoICBM = geoICBM,
      geoPosition = geoPosition,
      geoRegion = geoRegion,
      geoPlacename = geoPlacename,
      googleSiteVerification = googleSiteVerification,
      ogUrl = ogUrl,
      ogType = ogType,
      ogTitle = ogTitle,
      ogImage = ogImage,
      ogImageAlt = ogImageAlt,
      ogDescription = ogDescription,
      ogSiteName = ogSiteName,
      ogLocale = ogLocale,
      articleAuthor = Option(articleAuthor)
    )
}
