package ba.sake.hepek.html.pwa

import upickle.implicits.key

import ba.sake.hepek.html.utils.HepekPickler.{ReadWriter => RW, _}
import ba.sake.stone.Wither

@Wither
final case class WebAppManifest(
    name: String,
    @key("short_name")
    shortName: Option[String] = None,
    description: Option[String] = None,
    @key("theme_color")
    themeColor: Option[String] = None,
    @key("background_color")
    backgroundColor: Option[String] = None,
    icons: List[WebAppManifestIcon] = List.empty,
    display: String = "minimal-ui", // fullscreen, browser, standalone, minimal-ui
    orientation: Option[String] = None,
    @key("start_url")
    startUrl: String = ".",
    scope: Option[String] = None,
    serviceworker: Option[WebAppServiceWorker] = None,
    categories: List[String] = List.empty,
    dir: Option[String] = None, // rtl, ltr, auto
    lang: Option[String] = None,
    @key("prefer_related_applications")
    preferRelatedApplications: Boolean = false,
    @key("related_applications")
    relatedApplications: List[WebAppRelatedApplication] = List.empty,
    screenshots: List[WebAppScreenshot] = List.empty
)

object WebAppManifest {
  implicit val rw: RW[WebAppManifest] = macroRW
}

@Wither
final case class WebAppManifestIcon(
    src: String,
    sizes: String,
    @key("type")
    tpe: String,
    purpose: Option[String] = None
)

object WebAppManifestIcon {
  implicit val rw: RW[WebAppManifestIcon] = macroRW
}

@Wither
final case class WebAppRelatedApplication(
    platform: String,
    url: String,
    id: String
)

object WebAppRelatedApplication {
  implicit val rw: RW[WebAppRelatedApplication] = macroRW
}

@Wither
final case class WebAppScreenshot(
    src: String,
    sizes: String,
    @key("type")
    tpe: String
)

object WebAppScreenshot {
  implicit val rw: RW[WebAppScreenshot] = macroRW
}

@Wither
final case class WebAppServiceWorker(
    src: String,
    scope: Option[String] = None,
    @key("type")
    tpe: Option[String] = None,
    @key("update_via_cache") updateViaCache: Option[String] = None
)

object WebAppServiceWorker {
  implicit val rw: RW[WebAppServiceWorker] = macroRW
}
