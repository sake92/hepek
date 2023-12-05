package ba.sake.hepek.html

final class SiteSettings private (
    val name: Option[String],
    val faviconNormal: Option[String],
    val faviconInverted: Option[String]
) {

  def withName(name: Option[String]): SiteSettings =
    copy(name = name)

  def withName(name: String): SiteSettings =
    copy(name = Option(name))

  def withFaviconNormal(faviconNormal: Option[String]): SiteSettings =
    copy(faviconNormal = faviconNormal)

  def withFaviconNormal(faviconNormal: String): SiteSettings =
    copy(faviconNormal = Option(faviconNormal))

  def withFaviconInverted(faviconInverted: Option[String]): SiteSettings =
    copy(faviconInverted = faviconInverted)

  def withFaviconInverted(faviconInverted: String): SiteSettings =
    copy(faviconInverted = Option(faviconInverted))

  private def copy(
      name: Option[String] = name,
      faviconNormal: Option[String] = faviconNormal,
      faviconInverted: Option[String] = faviconInverted
  ) =
    new SiteSettings(name, faviconNormal, faviconInverted)
}

object SiteSettings:
  val default: SiteSettings = new SiteSettings(None, None, None)
