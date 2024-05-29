package ba.sake.hepek.html

final class MetaSettings private (
    val charset: String,
    val xuaCompatible: String,
    val viewport: String,
    val themeColor: String,
    // page
    val subject: Option[String],
    val first: Option[String],
    val last: Option[String],
    val prev: Option[String],
    val next: Option[String],
    val editURI: Option[String],
    // geo
    val geoICBM: Option[String],
    val geoPosition: Option[String],
    val geoRegion: Option[String],
    val geoPlacename: Option[String],
    // open graph fb and others
    val ogUrl: Option[String],
    val ogType: Option[String],
    val ogTitle: Option[String],
    val ogImage: Option[String],
    val ogImageAlt: Option[String],
    val ogDescription: Option[String],
    val ogSiteName: Option[String],
    val ogLocale: Option[String],
    val articleAuthor: Option[String],
    // twitter card
    val twitterCard: Option[String], // usually set to "summary_large_image"
    val twitterSite: Option[String], // The Twitter @username of author
    val twitterTitle: Option[String],
    val twitterDescription: Option[String],
    val twitterImage: Option[String],
    val twitterImageAlt: Option[String]
) {

  def withCharset(charset: String): MetaSettings = copy(charset = charset)

  def withXuaCompatible(xuaCompatible: String): MetaSettings = copy(xuaCompatible = xuaCompatible)

  def withViewport(viewport: String): MetaSettings = copy(viewport = viewport)

  def withThemeColor(themeColor: String): MetaSettings = copy(themeColor = themeColor)

  def withSubject(subject: Option[String]): MetaSettings = copy(subject = subject)

  def withSubject(subject: String): MetaSettings = withSubject(Option(subject))

  def withFirst(first: Option[String]): MetaSettings = copy(first = first)

  def withFirst(first: String): MetaSettings = withFirst(Option(first))

  def withLast(last: Option[String]): MetaSettings = copy(last = last)

  def withLast(last: String): MetaSettings = withLast(Option(last))

  def withPrev(prev: Option[String]): MetaSettings = copy(prev = prev)

  def withPrev(prev: String): MetaSettings = withPrev(Option(prev))

  def withNext(next: Option[String]): MetaSettings = copy(next = next)

  def withNext(next: String): MetaSettings = withNext(Option(next))

  def withEditURI(editURI: Option[String]): MetaSettings = copy(editURI = editURI)

  def withEditURI(editURI: String): MetaSettings = withEditURI(Option(editURI))

  def withGeoICBM(geoICBM: Option[String]): MetaSettings = copy(geoICBM = geoICBM)

  def withGeoICBM(geoICBM: String): MetaSettings = withGeoICBM(Option(geoICBM))

  def withGeoPosition(geoPosition: Option[String]): MetaSettings = copy(geoPosition = geoPosition)

  def withGeoPosition(geoPosition: String): MetaSettings = withGeoPosition(Option(geoPosition))

  def withGeoRegion(geoRegion: Option[String]): MetaSettings = copy(geoRegion = geoRegion)

  def withGeoRegion(geoRegion: String): MetaSettings = withGeoRegion(Option(geoRegion))

  def withGeoPlacename(geoPlacename: Option[String]): MetaSettings =
    copy(geoPlacename = geoPlacename)

  def withGeoPlacename(geoPlacename: String): MetaSettings = withGeoPlacename(Option(geoPlacename))

  def withOgUrl(ogUrl: Option[String]): MetaSettings = copy(ogUrl = ogUrl)

  def withOgUrl(ogUrl: String): MetaSettings = withOgUrl(Option(ogUrl))

  def withOgType(ogType: Option[String]): MetaSettings = copy(ogType = ogType)

  def withOgType(ogType: String): MetaSettings = withOgType(Option(ogType))

  def withOgTitle(ogTitle: Option[String]): MetaSettings = copy(ogTitle = ogTitle)

  def withOgTitle(ogTitle: String): MetaSettings = withOgTitle(Option(ogTitle))

  def withOgImage(ogImage: Option[String]): MetaSettings = copy(ogImage = ogImage)

  def withOgImage(ogImage: String): MetaSettings = withOgImage(Option(ogImage))

  def withOgImageAlt(ogImageAlt: Option[String]): MetaSettings = copy(ogImageAlt = ogImageAlt)

  def withOgImageAlt(ogImageAlt: String): MetaSettings = withOgImageAlt(Option(ogImageAlt))

  def withOgDescription(ogDescription: Option[String]): MetaSettings =
    copy(ogDescription = ogDescription)

  def withOgDescription(ogDescription: String): MetaSettings = withOgDescription(
    Some(ogDescription)
  )

  def withOgSiteName(ogSiteName: Option[String]): MetaSettings = copy(ogSiteName = ogSiteName)

  def withOgSiteName(ogSiteName: String): MetaSettings = withOgSiteName(Option(ogSiteName))

  def withOgLocale(ogLocale: Option[String]): MetaSettings = copy(ogLocale = ogLocale)

  def withOgLocale(ogLocale: String): MetaSettings = withOgLocale(Option(ogLocale))

  def withArticleAuthor(articleAuthor: Option[String]): MetaSettings =
    copy(articleAuthor = articleAuthor)

  def withArticleAuthor(articleAuthor: String): MetaSettings = withArticleAuthor(
    Option(articleAuthor)
  )

  def withTwitterCard(twitterCard: Option[String]): MetaSettings =
    copy(twitterCard = twitterCard)

  def withTwitterCard(twitterCard: String): MetaSettings = withArticleAuthor(
    Option(twitterCard)
  )

  def withTwitterSite(twitterSite: Option[String]): MetaSettings =
    copy(twitterSite = twitterSite)

  def withTwitterSite(twitterSite: String): MetaSettings = withArticleAuthor(
    Option(twitterSite)
  )

  def withTwitterTitle(twitterTitle: Option[String]): MetaSettings =
    copy(twitterTitle = twitterTitle)

  def withTwitterTitle(twitterTitle: String): MetaSettings = withArticleAuthor(
    Option(twitterTitle)
  )

  def withTwitterDescription(twitterDescription: Option[String]): MetaSettings =
    copy(twitterDescription = twitterDescription)

  def withTwitterDescription(twitterDescription: String): MetaSettings = withArticleAuthor(
    Option(twitterDescription)
  )

  def withTwitterImage(twitterImage: Option[String]): MetaSettings =
    copy(twitterImage = twitterImage)

  def withTwitterImage(twitterImage: String): MetaSettings = withArticleAuthor(
    Option(twitterImage)
  )

  def withTwitterImageAlt(twitterImageAlt: Option[String]): MetaSettings =
    copy(twitterImageAlt = twitterImageAlt)

  def withTwitterImageAlt(twitterImageAlt: String): MetaSettings = withArticleAuthor(
    Option(twitterImageAlt)
  )

  private def copy(
      charset: String = charset,
      xuaCompatible: String = xuaCompatible,
      viewport: String = viewport,
      themeColor: String = themeColor,
      subject: Option[String] = subject,
      first: Option[String] = first,
      last: Option[String] = last,
      prev: Option[String] = prev,
      next: Option[String] = next,
      editURI: Option[String] = editURI,
      geoICBM: Option[String] = geoICBM,
      geoPosition: Option[String] = geoPosition,
      geoRegion: Option[String] = geoRegion,
      geoPlacename: Option[String] = geoPlacename,
      ogUrl: Option[String] = ogUrl,
      ogType: Option[String] = ogType,
      ogTitle: Option[String] = ogTitle,
      ogImage: Option[String] = ogImage,
      ogImageAlt: Option[String] = ogImageAlt,
      ogDescription: Option[String] = ogDescription,
      ogSiteName: Option[String] = ogSiteName,
      ogLocale: Option[String] = ogLocale,
      articleAuthor: Option[String] = articleAuthor,
      twitterCard: Option[String] = twitterCard,
      twitterSite: Option[String] = twitterSite,
      twitterTitle: Option[String] = twitterTitle,
      twitterDescription: Option[String] = twitterDescription,
      twitterImage: Option[String] = twitterImage,
      twitterImageAlt: Option[String] = twitterImageAlt
  ) =
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
      twitterCard = twitterCard,
      twitterSite = twitterSite,
      twitterTitle = twitterTitle,
      twitterDescription = twitterDescription,
      twitterImage = twitterImage,
      twitterImageAlt = twitterImageAlt
    )
}

object MetaSettings:
  val default: MetaSettings = new MetaSettings(
    charset = "utf-8",
    xuaCompatible = "ie=edge",
    viewport = "width=device-width, initial-scale=1",
    themeColor = "#000",
    subject = None,
    first = None,
    last = None,
    prev = None,
    next = None,
    editURI = None,
    geoICBM = None,
    geoPosition = None,
    geoRegion = None,
    geoPlacename = None,
    ogUrl = None,
    ogType = None,
    ogTitle = None,
    ogImage = None,
    ogImageAlt = None,
    ogDescription = None,
    ogSiteName = None,
    ogLocale = None,
    articleAuthor = None,
    twitterCard = Some("summary_large_image"),
    twitterSite = None,
    twitterTitle = None,
    twitterDescription = None,
    twitterImage = None,
    twitterImageAlt = None
  )
