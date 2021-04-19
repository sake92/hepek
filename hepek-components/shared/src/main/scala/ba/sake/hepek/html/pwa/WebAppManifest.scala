package ba.sake.hepek.html.pwa

import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._
import ba.sake.kalem.Wither

@Wither
final case class WebAppManifest(
    name: String,
    @named("short_name")
    shortName: Option[String] = None,
    description: Option[String] = None,
    @named("theme_color")
    themeColor: Option[String] = None,
    @named("background_color")
    backgroundColor: Option[String] = None,
    icons: List[WebAppManifestIcon] = List.empty,
    display: String = "minimal-ui", // fullscreen, browser, standalone, minimal-ui
    orientation: Option[String] = None,
    @named("start_url")
    startUrl: String = ".",
    scope: Option[String] = None,
    serviceworker: Option[WebAppServiceWorker] = None,
    categories: List[String] = List.empty,
    dir: Option[String] = None, // rtl, ltr, auto
    lang: Option[String] = None,
    @named("prefer_related_applications")
    preferRelatedApplications: Boolean = false,
    @named("related_applications")
    relatedApplications: List[WebAppRelatedApplication] = List.empty,
    screenshots: List[WebAppScreenshot] = List.empty
) {

  def withName(name: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withShortName(shortName: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withShortName(shortName: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      shortName = Option(shortName)
    )

  def withDescription(description: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withDescription(description: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      description = Option(description)
    )

  def withThemeColor(themeColor: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withThemeColor(themeColor: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      themeColor = Option(themeColor)
    )

  def withBackgroundColor(backgroundColor: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withBackgroundColor(backgroundColor: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      backgroundColor = Option(backgroundColor)
    )

  def withIcons(icons: List[WebAppManifestIcon]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withIcons(icons: WebAppManifestIcon*): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      icons = icons.toList
    )

  def withDisplay(display: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withOrientation(orientation: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withOrientation(orientation: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      orientation = Option(orientation)
    )

  def withStartUrl(startUrl: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withScope(scope: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withScope(scope: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      scope = Option(scope)
    )

  def withServiceworker(serviceworker: Option[WebAppServiceWorker]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withServiceworker(serviceworker: WebAppServiceWorker): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      serviceworker = Option(serviceworker)
    )

  def withCategories(categories: List[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withCategories(categories: String*): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      categories = categories.toList
    )

  def withDir(dir: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withDir(dir: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      dir = Option(dir)
    )

  def withLang(lang: Option[String]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withLang(lang: String): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots,
      lang = Option(lang)
    )

  def withPreferRelatedApplications(preferRelatedApplications: Boolean): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withRelatedApplications(relatedApplications: List[WebAppRelatedApplication]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withRelatedApplications(relatedApplications: WebAppRelatedApplication*): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      screenshots = screenshots,
      relatedApplications = relatedApplications.toList
    )

  def withScreenshots(screenshots: List[WebAppScreenshot]): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots
    )

  def withScreenshots(screenshots: WebAppScreenshot*): WebAppManifest =
    new WebAppManifest(
      name = name,
      shortName = shortName,
      description = description,
      themeColor = themeColor,
      backgroundColor = backgroundColor,
      icons = icons,
      display = display,
      orientation = orientation,
      startUrl = startUrl,
      scope = scope,
      serviceworker = serviceworker,
      categories = categories,
      dir = dir,
      lang = lang,
      preferRelatedApplications = preferRelatedApplications,
      relatedApplications = relatedApplications,
      screenshots = screenshots.toList
    )
}

object WebAppManifest {
  implicit val rw: JsonValueCodec[WebAppManifest] = JsonCodecMaker.make
}

@Wither
final case class WebAppManifestIcon(
    src: String,
    sizes: String,
    @named("type")
    tpe: String,
    purpose: Option[String] = None
) {

  def withSrc(src: String): WebAppManifestIcon =
    new WebAppManifestIcon(src = src, sizes = sizes, tpe = tpe, purpose = purpose)

  def withSizes(sizes: String): WebAppManifestIcon =
    new WebAppManifestIcon(src = src, sizes = sizes, tpe = tpe, purpose = purpose)

  def withTpe(tpe: String): WebAppManifestIcon =
    new WebAppManifestIcon(src = src, sizes = sizes, tpe = tpe, purpose = purpose)

  def withPurpose(purpose: Option[String]): WebAppManifestIcon =
    new WebAppManifestIcon(src = src, sizes = sizes, tpe = tpe, purpose = purpose)

  def withPurpose(purpose: String): WebAppManifestIcon =
    new WebAppManifestIcon(src = src, sizes = sizes, tpe = tpe, purpose = Option(purpose))
}

object WebAppManifestIcon {
  implicit val rw: JsonValueCodec[WebAppManifestIcon] = JsonCodecMaker.make
}

@Wither
final case class WebAppRelatedApplication(
    platform: String,
    url: String,
    id: String
) {

  def withPlatform(platform: String): WebAppRelatedApplication =
    new WebAppRelatedApplication(platform = platform, url = url, id = id)

  def withUrl(url: String): WebAppRelatedApplication =
    new WebAppRelatedApplication(platform = platform, url = url, id = id)

  def withId(id: String): WebAppRelatedApplication =
    new WebAppRelatedApplication(platform = platform, url = url, id = id)
}

object WebAppRelatedApplication {
  implicit val rw: JsonValueCodec[WebAppRelatedApplication] = JsonCodecMaker.make
}

@Wither
final case class WebAppScreenshot(
    src: String,
    sizes: String,
    @named("type")
    tpe: String
) {

  def withSrc(src: String): WebAppScreenshot =
    new WebAppScreenshot(src = src, sizes = sizes, tpe = tpe)

  def withSizes(sizes: String): WebAppScreenshot =
    new WebAppScreenshot(src = src, sizes = sizes, tpe = tpe)

  def withTpe(tpe: String): WebAppScreenshot =
    new WebAppScreenshot(src = src, sizes = sizes, tpe = tpe)
}

object WebAppScreenshot {
  implicit val rw: JsonValueCodec[WebAppScreenshot] = JsonCodecMaker.make
}

@Wither
final case class WebAppServiceWorker(
    src: String,
    scope: Option[String] = None,
    @named("type")
    tpe: Option[String] = None,
    @named("update_via_cache") updateViaCache: Option[String] = None
) {

  def withSrc(src: String): WebAppServiceWorker =
    new WebAppServiceWorker(src = src, scope = scope, tpe = tpe, updateViaCache = updateViaCache)

  def withScope(scope: Option[String]): WebAppServiceWorker =
    new WebAppServiceWorker(src = src, scope = scope, tpe = tpe, updateViaCache = updateViaCache)

  def withScope(scope: String): WebAppServiceWorker =
    new WebAppServiceWorker(
      src = src,
      tpe = tpe,
      updateViaCache = updateViaCache,
      scope = Option(scope)
    )

  def withTpe(tpe: Option[String]): WebAppServiceWorker =
    new WebAppServiceWorker(src = src, scope = scope, tpe = tpe, updateViaCache = updateViaCache)

  def withTpe(tpe: String): WebAppServiceWorker =
    new WebAppServiceWorker(
      src = src,
      scope = scope,
      updateViaCache = updateViaCache,
      tpe = Option(tpe)
    )

  def withUpdateViaCache(updateViaCache: Option[String]): WebAppServiceWorker =
    new WebAppServiceWorker(src = src, scope = scope, tpe = tpe, updateViaCache = updateViaCache)

  def withUpdateViaCache(updateViaCache: String): WebAppServiceWorker =
    new WebAppServiceWorker(
      src = src,
      scope = scope,
      tpe = tpe,
      updateViaCache = Option(updateViaCache)
    )
}

object WebAppServiceWorker {
  implicit val rw: JsonValueCodec[WebAppServiceWorker] = JsonCodecMaker.make
}
