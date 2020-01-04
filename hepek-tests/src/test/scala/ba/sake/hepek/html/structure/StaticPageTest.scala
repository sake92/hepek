package ba.sake.hepek.html.structure

import ba.sake.hepek.selenium.HepekSeleniumTest

class StaticPageTest extends HepekSeleniumTest {
  fixtures.static.StaticPages.all.foreach { p =>
    s"Static page [${p.getClass()}]" should "have the correct title, favicon etc." in {
      go to filePath(p)
      // <title>
      pageTitle should be(p.pageSettings.title + " - " + p.siteSettings.name.get)
      // <link rel="shortcut icon" href="favicon" >
      val favicon = find(cssSelector("link[rel='shortcut icon']"))
      favicon.get.attribute("href").get should endWith(p.siteSettings.faviconNormal.get)
      // <meta name="description" content="Page description" />
      val description = find(cssSelector("meta[name='description']"))
      description.get.attribute("content").get should endWith(p.pageSettings.description.get)
      // <html lang="bs">
      val lang = find(cssSelector("html"))
      lang.get.attribute("lang").get should endWith(p.pageSettings.language)
    }
  }
}
