package ba.sake.hepek.html

import scala.jdk.CollectionConverters.*
import org.jsoup.Jsoup
import ba.sake.hepek.htmx.HtmxDependencies
import ba.sake.hepek.bootstrap5.BootstrapDependencies

class HtmlPageTest extends munit.FunSuite {

  test("<head> should have a <title>") {
    val document = Jsoup.parse(TestHtmlPage.contents.render)
    val titleTags =
      document.head().getElementsByTag("title").iterator().asScala.toList
    assertEquals(titleTags.size, 1)
    assertEquals(titleTags.head.text(), "Page Title")
  }

  test("<head> should have <meta> tags first") {
    val document = Jsoup.parse(TestHtmlPage.contents.render)
    val allMetaTags =
      document.head().getElementsByTag("meta").iterator().asScala.toList.map(_.outerHtml())
    val firstMetaTags = document
      .head()
      .children()
      .iterator()
      .asScala
      .toList
      .takeWhile(_.tagName() == "meta")
      .map(_.outerHtml())
    assertEquals(
      allMetaTags,
      firstMetaTags,
      "meta tags must come first"
    )
  }

  test("<head> should have style URLs and then inline styles (to be able to override)") {
    val document = Jsoup.parse(TestHtmlPage.contents.render)
    val (styleUrlElems, inlineStyleElems) = document
      .head()
      .children()
      .iterator()
      .asScala
      .toList
      .filter(e =>
        (e.tagName() == "link" && e.attr("rel") == "stylesheet") || e.tagName() == "style"
      )
      .span(_.tagName() == "link")
    styleUrlElems.foreach { elem =>
      assertEquals(elem.tagName(), "link")
    }
    inlineStyleElems.foreach { elem =>
      assertEquals(elem.tagName(), "style")
    }
  }

  test("<head> should have component dependency URLs") {
    val document = Jsoup.parse(TestHtmlPage.contents.render)
    val styleUrlElems = document
      .head()
      .children()
      .iterator()
      .asScala
      .toList
      .filter(e => e.tagName() == "link" && e.attr("rel") == "stylesheet")

    assert(
      styleUrlElems.exists(_.attr("href").endsWith("bootstrap.min.css"))
    )

    val scriptUrlElems = document
      .body()
      .children()
      .iterator()
      .asScala
      .toList
      .filter(e => e.tagName() == "script" && e.attr("src") != "")
    assert(
      scriptUrlElems.exists(_.attr("src").endsWith("bootstrap.bundle.min.js"))
    )
    assert(
      scriptUrlElems.exists(_.attr("src").endsWith("htmx.min.js"))
    )
  }

  test("<body> should have script URLs and then inline scripts (to be able to override)") {
    val document = Jsoup.parse(TestHtmlPage.contents.render)
    val (scriptUrlElems, inlineScriptElems) = document
      .body()
      .children()
      .iterator()
      .asScala
      .toList
      .filter(_.tagName() == "script")
      .span(_.attr("src") != "")
    scriptUrlElems.foreach { elem =>
      assert(elem.attr("src") != "")
    }
    inlineScriptElems.foreach { elem =>
      assert(elem.attr("src") == "")
    }
  }
}

object TestHtmlPage extends HtmlPage with HtmxDependencies with BootstrapDependencies {

  override def pageSettings: PageSettings = super.pageSettings.withTitle("Page Title")

  override def styleURLs: List[String]    = List("/mystyle.css")
  override def stylesInline: List[String] = List("div { color: white }")

  override def scriptURLs: List[String]    = List("/myscript.js")
  override def scriptsInline: List[String] = List("console.log('inline')")
}

/*
<html lang="en">
 <head>ba.sake.hepek.html.HtmlPageTest 0s
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="generator" content="hepek">
  <meta name="theme-color" content="#000">
  <meta name="mobile-web-app-capable" content="yes">
  <meta name="twitter:card" content="summary_large_image">
  <title>Page Title</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="/mystyle.css">
  <style>div { color: white }</style>
 </head>
 <body>
  <script src="https://unpkg.com/htmx.org@1.9.10/dist/htmx.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
  <script src="/myscript.js"></script>
  <script>console.log('inline')</script>
 </body>
</html>
 */
