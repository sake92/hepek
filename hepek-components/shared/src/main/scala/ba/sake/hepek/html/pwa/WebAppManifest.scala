package ba.sake.hepek.html.pwa

import ba.sake.tupson.JsonRW

final case class WebAppManifest(
    name: String,
    short_name: Option[String] = None,
    description: Option[String] = None,
    theme_color: Option[String] = None,
    background_color: Option[String] = None,
    icons: List[WebAppManifestIcon] = List.empty,
    display: String = "minimal-ui", // "fullscreen", "browser",  "standalone", "minimal-ui"
    orientation: Option[String] = None,
    start_url: String = ".",
    scope: Option[String] = None,
    serviceworker: Option[WebAppServiceWorker] = None,
    categories: List[String] = List.empty,
    dir: Option[String] = None, // rtl, ltr, auto
    lang: Option[String] = None,
    prefer_related_applications: Boolean = false,
    related_applications: List[WebAppRelatedApplication] = List.empty,
    screenshots: List[WebAppScreenshot] = List.empty
) derives JsonRW

final case class WebAppManifestIcon(
    src: String,
    sizes: String,
    `type`: String,
    purpose: Option[String] = None
)

final case class WebAppRelatedApplication(
    platform: String,
    url: String,
    id: String
)

final case class WebAppScreenshot(
    src: String,
    sizes: String,
    `type`: String
)

final case class WebAppServiceWorker(
    src: String,
    scope: Option[String] = None,
    `type`: Option[String] = None,
    update_via_cache: Option[String] = None
)
