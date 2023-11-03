package ba.sake.hepek.html.structure

import ba.sake.hepek.selenium.HepekSeleniumTest
import fixtures.static.*

class StaticPageTest extends HepekSeleniumTest {
  val p = new SimpleStaticPage {}

  /*s"Static page [${p.getClass()}]" should "have the correct title, favicon etc." in {
      go to filePath(p)

      // <html lang="bs">
      getByCss("html").get.attribute("lang").get should equal(p.pageSettings.language)

      // <title>
      pageTitle should be(p.pageSettings.title + " - " + p.siteSettings.name.get)

      // <link>
      getByCss("link[rel='shortcut icon']").get.attribute("href").get should endWith(
        p.siteSettings.faviconNormal.get
      )

      // <meta>
      getByCss("meta[charset]").get.attribute("charset").get should equal(
        p.metaSettings.charset
      )
      getByCss("meta[http-equiv]").get.attribute("content").get should equal(
        p.metaSettings.xuaCompatible
      )
      getByCss("meta[name='viewport']").get.attribute("content").get should equal(
        p.metaSettings.viewport
      )
      getByCss("meta[name='generator']").get.attribute("content").get should equal("hepek")
      getByCss("meta[name='theme-color']").get.attribute("content").get should equal(
        p.metaSettings.themeColor
      )
      getByCss("meta[name='mobile-web-app-capable']").get.attribute("content").get should equal(
        "yes"
      )
      getByCss("meta[name='theme-color']").get.attribute("content").get should equal(
        p.metaSettings.themeColor
      )
      getByCss("meta[name='description']").get.attribute("content").get should equal(
        p.pageSettings.description.get
      )
      p.metaSettings.first.map(
        stuff => getByCss("meta[name='first']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.last
        .map(
          stuff => getByCss("meta[name='last']").get.attribute("content").get should equal(stuff)
        )
      p.metaSettings.prev
        .map(
          stuff => getByCss("meta[name='prev']").get.attribute("content").get should equal(stuff)
        )
      p.metaSettings.next
        .map(
          stuff => getByCss("meta[name='next']").get.attribute("content").get should equal(stuff)
        )
      p.metaSettings.editURI.map(
        stuff => getByCss("meta[name='EditURI']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.subject.map(
        stuff => getByCss("meta[name='subject']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.geoICBM
        .map(
          stuff => getByCss("meta[name='ICBM']").get.attribute("content").get should equal(stuff)
        )
      p.metaSettings.geoPosition.map(
        stuff =>
          getByCss("meta[name='geo.position']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.geoRegion.map(
        stuff =>
          getByCss("meta[name='geo.region']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.geoPlacename.map(
        stuff =>
          getByCss("meta[name='geo.placename']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.googleSiteVerification.map(
        stuff =>
          getByCss("meta[name='google-site-verification']").get
            .attribute("content")
            .get should equal(stuff)
      )

      // Open Graph stuff
      p.metaSettings.ogUrl.map(
        stuff => getByCss("meta[name='og:url']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogType.map(
        stuff => getByCss("meta[name='og:type']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogTitle.map(
        stuff => getByCss("meta[name='og:title']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogImage.map(
        stuff => getByCss("meta[name='og:image']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogImageAlt.map(
        stuff =>
          getByCss("meta[name='og:image:alt']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogDescription.map(
        stuff =>
          getByCss("meta[name='og:description']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogSiteName.map(
        stuff =>
          getByCss("meta[name='og:site_name']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.ogLocale.map(
        stuff => getByCss("meta[name='og:locale']").get.attribute("content").get should equal(stuff)
      )
      p.metaSettings.articleAuthor.map(
        stuff =>
          getByCss("meta[name='article:author']").get.attribute("content").get should equal(stuff)
      )
    }*/

}
