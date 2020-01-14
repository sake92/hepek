package ba.sake.hepek.html.pwa

import ba.sake.stone.Wither
import ba.sake.hepek.html.utils.HepekPickler.{ReadWriter => RW, _}

@Wither
case class WebAppManifest(
    name: String,
    @upickle.implicits.key("short_name")
    shortName: Option[String] = None,
    description: Option[String] = None,
    @upickle.implicits.key("theme_color")
    themeColor: Option[String] = None,
    @upickle.implicits.key("background_color")
    backgroundColor: Option[String] = None,
    display: String = "minimal-ui", // fullscreen, browser, standalone, minimal-ui
    orientation: Option[String] = None,
    @upickle.implicits.key("start_url")
    startUrl: String = ".",
    scope: Option[String] = None,
    serviceworker: Option[WebAppServiceWorker] = None,
    categories: List[String] = List.empty,
    dir: Option[String] = None, // rtl, ltr, auto
    lang: Option[String] = None,
    @upickle.implicits.key("prefer_related_applications")
    preferRelatedApplications: Boolean = false,
    @upickle.implicits.key("related_applications")
    relatedApplications: List[WebAppRelatedApplication] = List.empty,
    screenshots: List[WebAppScreenshot] = List.empty
)

object WebAppManifest {
  implicit val rw: RW[WebAppManifest] = macroRW
}

@Wither
case class WebAppManifestIcon(
    src: String,
    sizes: String,
    @upickle.implicits.key("type")
    tpe: String,
    purpose: Option[String] = None
)

object WebAppManifestIcon {
  implicit val rw: RW[WebAppManifestIcon] = macroRW
}

@Wither
case class WebAppRelatedApplication(
    platform: String,
    url: String,
    id: String
)

object WebAppRelatedApplication {
  implicit val rw: RW[WebAppRelatedApplication] = macroRW
}

@Wither
case class WebAppScreenshot(
    src: String,
    sizes: String,
    @upickle.implicits.key("type")
    tpe: String
)

object WebAppScreenshot {
  implicit val rw: RW[WebAppScreenshot] = macroRW
}

@Wither
case class WebAppServiceWorker(
    src: String,
    scope: Option[String] = None,
    @upickle.implicits.key("type")
    tpe: Option[String] = None,
    @upickle.implicits.key("update_via_cache") updateViaCache: Option[String] = None
)

object WebAppServiceWorker {
  implicit val rw: RW[WebAppServiceWorker] = macroRW
}
