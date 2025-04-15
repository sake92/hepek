package ba.sake.hepek

package html

import ba.sake.hepek.utils.MimeTypes
import ba.sake.hepek.html.pwa.WebAppManifest
import ba.sake.hepek.markdown.*
import ba.sake.hepek.scalatags.all.{html as htmlTag, *}
import ba.sake.hepek.scalatags.tags2.style as styleTag

trait HtmlPage extends PageDependencies {

  // "".md syntax support
  def markdownHandler: MarkdownHandler   = DefaultMarkdownHandler()
  def markdownSettings: MarkdownSettings = MarkdownSettings()
  extension (str: String)
    def md: Frag = markdownHandler.render(
      str,
      suppressHTML = markdownSettings.suppressHTML,
      escapeHTML = markdownSettings.escapeHTML
    )

  def siteSettings: SiteSettings = SiteSettings.default

  def pageSettings: PageSettings = PageSettings.default

  def metaSettings: MetaSettings =
    MetaSettings.default
      .withOgTitle(siteSettings.name)

  def manifest: WebAppManifest = WebAppManifest(
    name = siteSettings.name.getOrElse(""),
    lang = Some(pageSettings.language)
  )

  /** `<html>` and everything inside it
    */
  def contents: Frag = {
    // separate from headContent, because it is easier to override headContent this way:
    // override def headContent = frag(super.headContent, ...)
    // and stylesInline will always come last, where we usually override CSS
    val allInlineStyles = locally {
      val compStyleInlines = components.flatMap(_._2.cssDependencies.inlines)
      compStyleInlines ++ stylesInline
    }
    // same for scripts
    val allInlineScripts = locally {
      val compScriptInlines = components.flatMap(_._2.jsDependencies.inlines)
      compScriptInlines ++ scriptsInline
    }
    htmlTag(lang := pageSettings.language)(
      head(
        headContent,
        allInlineStyles.map(s => styleTag(raw(s)))
      ),
      body(bodyAttrs)(
        bodyContent,
        allInlineScripts.map(s => script(tpe := "module")(raw(s)))
      )
    )
  }

  // <meta>
  private val property = attr("property")
  def metaTags: Seq[Frag] = Seq(
    meta(charset            := metaSettings.charset),
    meta(attr("http-equiv") := "X-UA-Compatible", content        := metaSettings.xuaCompatible),
    meta(name               := "viewport", content               := metaSettings.viewport),
    meta(name               := "generator", content              := "hepek"),
    meta(name               := "theme-color", content            := metaSettings.themeColor),
    meta(name               := "mobile-web-app-capable", content := "yes"),
    // page
    pageSettings.description.map(c => meta(name := "description", content := c)),
    metaSettings.first.map(c => meta(name := "first", content := c)),
    metaSettings.last.map(c => meta(name := "last", content := c)),
    metaSettings.prev.map(c => meta(name := "prev", content := c)),
    metaSettings.next.map(c => meta(name := "next", content := c)),
    metaSettings.editURI.map(c => meta(name := "EditURI", content := c)),
    metaSettings.subject.map(c => meta(name := "subject", content := c)),
    // geo
    metaSettings.geoICBM.map(c => meta(name := "ICBM", content := c)),
    metaSettings.geoPosition.map(c => meta(name := "geo.position", content := c)),
    metaSettings.geoRegion.map(c => meta(name := "geo.region", content := c)),
    metaSettings.geoPlacename.map(c => meta(name := "geo.placename", content := c)),
    // open graph
    metaSettings.ogUrl.map(c => meta(property := "og:url", content := c)),
    metaSettings.ogType.map(c => meta(property := "og:type", content := c)),
    metaSettings.ogTitle.map(c => meta(property := "og:title", content := c)),
    metaSettings.ogImage.map(c => meta(property := "og:image", content := c)),
    metaSettings.ogImageAlt.map(c => meta(property := "og:image:alt", content := c)),
    metaSettings.ogDescription.map(c => meta(property := "og:description", content := c)),
    metaSettings.ogSiteName.map(c => meta(property := "og:site_name", content := c)),
    metaSettings.ogLocale.map(c => meta(property := "og:locale", content := c)),
    metaSettings.articleAuthor.map(c => meta(property := "article:author", content := c)),
    // twitter
    metaSettings.twitterTitle.map(c => meta(name := "twitter:title", content := c)),
    metaSettings.twitterCard.map(c => meta(name := "twitter:card", content := c)),
    metaSettings.twitterSite.map(c => meta(name := "twitter:site", content := c)),
    metaSettings.twitterDescription.map(c => meta(name := "twitter:description", content := c)),
    metaSettings.twitterImage.map(c => meta(name := "twitter:image", content := c)),
    metaSettings.twitterImageAlt.map(c => meta(name := "twitter:image:alt", content := c))
  )

  // <head>
  def headContent: Frag = {
    val allStyleURLs = locally {
      val compStyleUrls = components.flatMap { case (cs, cd) =>
        cd.cssDependencies.urls ++
          cd.cssDependencies.deps.map(cs.depsProvider.depPath)
      }
      compStyleUrls ++ styleURLs
    }

    frag(
      metaTags,
      tag("title")(
        pageSettings.title + siteSettings.name.map(n => " - " + n).getOrElse("")
      ),
      siteSettings.faviconNormal.map { fav =>
        link(rel := "shortcut icon", href := fav, tpe := MimeTypes.guess(fav))
      },
      allStyleURLs.map(u => link(rel := "stylesheet", href := u))
    )
  }

  // <body>
  def bodyAttrs: Seq[AttrPair] = Seq.empty

  def bodyContent: Frag = {
    val allScriptURLs = locally {
      val compScriptUrls = components.flatMap { case (cs, cd) =>
        cd.jsDependencies.urls ++
          cd.jsDependencies.deps.map(cs.depsProvider.depPath)
      }
      compScriptUrls ++ scriptURLs
    }
    frag(
      pageContent,
      allScriptURLs.map(u => script(src := u))
    )
  }

  def pageContent: Frag = tag("main")(
    mainContent
  )

  def mainContent: Frag = frag()
}
