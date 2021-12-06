package ba.sake.hepek.pwa

import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import ba.sake.tupson.JsonWriter.toJson
import ba.sake.hepek.html.pwa._


class WebAppManifestTest extends AnyFlatSpec with Matchers {
  "WebAppManifest" should "serialize manifest correctly" in {
    val manifest = WebAppManifest("myName")
      .withShortName("myShortName")
      .withDescription("desc")
      .withThemeColor("#fff")
      .withBackgroundColor("#000")
      .withDisplay("fullscreen")
      .withOrientation("portrait-primary")
      .withStartUrl("https://example.com")
      .withScope("/app/")
      .withServiceworker(WebAppServiceWorker("./serviceworker.js"))
      .withCategories("a", "b")
      .withDir("rtl")
      .withLang("bs")
      .withPreferRelatedApplications(true)
      .withRelatedApplications(
        WebAppRelatedApplication(
          "play",
          "https://play.google.com/store/apps/details?id=com.example.app1",
          "com.example.app1"
        )
      )
      .withScreenshots(WebAppScreenshot("screenshot.webp", "1280x720", "image/webp"))

    val manifestJsonString = manifest.toJson
    
    manifestJsonString shouldBe """{"name": "myName", "short_name": "myShortName", "description": "desc", "theme_color": "#fff", "background_color": "#000", "icons": [], "display": "fullscreen", "orientation": "portrait-primary", "start_url": "https://example.com", "scope": "/app/", "serviceworker": {"src": "./serviceworker.js", "scope": null, "type": null, "update_via_cache": null}, "categories": ["a","b"], "dir": "rtl", "lang": "bs", "prefer_related_applications": true, "related_applications": [{"platform": "play", "url": "https://play.google.com/store/apps/details?id=com.example.app1", "id": "com.example.app1"}], "screenshots": [{"src": "screenshot.webp", "sizes": "1280x720", "type": "image/webp"}]}"""
  }
}
