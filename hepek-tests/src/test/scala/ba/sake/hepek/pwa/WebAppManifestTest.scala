package ba.sake.hepek.pwa

import org.scalatest._
import ba.sake.hepek.html.pwa._
import ba.sake.hepek.html.utils.HepekPickler._

class WebAppManifestTest extends FlatSpec with Matchers {
  "WebAppManifest" should "serialize the default correctly" in {
    val manifest     = WebAppManifest("My site")
    val manifestJson = write(manifest)
    val serManifest  = read[WebAppManifest](manifestJson) // roundtrip to validate.. :)
    manifest.display shouldBe "minimal-ui"
    serManifest shouldBe manifest
  }
  it should "serialize custom manifest correctly" in {
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

    val manifestJson = write(manifest)
    val serManifest = read[WebAppManifest](manifestJson)
    serManifest shouldBe manifest
  }
}
