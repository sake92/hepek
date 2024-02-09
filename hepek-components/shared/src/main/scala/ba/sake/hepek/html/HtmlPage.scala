package ba.sake.hepek

package html

import ba.sake.hepek.utils.MimeTypes
import ba.sake.hepek.html.pwa.WebAppManifest
import ba.sake.hepek.scalatags.all.{html => htmlTag, _}

trait HtmlPage extends PageDependencies {

  def siteSettings: SiteSettings = SiteSettings.default

  def pageSettings: PageSettings = PageSettings.default

  // TODO fill all defaults
  def metaSettings: MetaSettings =
    MetaSettings.default
      .withOgTitle(siteSettings.name)

  def manifest: WebAppManifest = WebAppManifest(
    name = siteSettings.name.getOrElse(""),
    lang = Some(pageSettings.language)
  )

  def bodyAttrs: Seq[AttrPair] = Seq.empty

  def contents: Frag = {
    // css
    val compStyleInlines = components.flatMap(_._2.cssDependencies.inlines)
    val allStyleInlines  = compStyleInlines ++ stylesInline
    val compStyleUrls = components.flatMap { case (cs, cd) =>
      cd.cssDependencies.urls ++
        cd.cssDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allStyleURLs = compStyleUrls ++ styleURLs

    // js
    val compScriptInlines = components.flatMap(_._2.jsDependencies.inlines)
    val allScriptInlines  = compScriptInlines ++ scriptsInline
    val compScriptUrls = components.flatMap { case (cs, cd) =>
      cd.jsDependencies.urls ++
        cd.jsDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allScriptURLs = compScriptUrls ++ scriptURLs

    htmlTag(lang := pageSettings.language)(
      head(
        headContent,
        allStyleURLs.map(u => link(rel := "stylesheet", href := u)) ++
          allStyleInlines.map(s => tag("style")(raw(s)))
      ),
      body(bodyAttrs)(
        bodyContent,
        allScriptURLs.map(u => script(src := u)) ++
          allScriptInlines.map(s => script(raw(s)))
      )
    )
  }

  // <head>
  def headContent: Frag = frag(
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
    metaSettings.ogUrl.map(c => meta(name := "og:url", content := c)),
    metaSettings.ogType.map(c => meta(name := "og:type", content := c)),
    metaSettings.ogTitle.map(c => meta(name := "og:title", content := c)),
    metaSettings.ogImage.map(c => meta(name := "og:image", content := c)),
    metaSettings.ogImageAlt.map(c => meta(name := "og:image:alt", content := c)),
    metaSettings.ogDescription.map(c => meta(name := "og:description", content := c)),
    metaSettings.ogSiteName.map(c => meta(name := "og:site_name", content := c)),
    metaSettings.ogLocale.map(c => meta(name := "og:locale", content := c)),
    metaSettings.articleAuthor.map(c => meta(name := "article:author", content := c)),
    // other
    tag("title")(
      pageSettings.title + siteSettings.name.map(n => " - " + n).getOrElse("")
    ),
    siteSettings.faviconNormal.map { fav =>
      link(rel := "shortcut icon", href := fav, tpe := MimeTypes.guess(fav))
    }
  )

  // <body>
  def bodyContent: Frag = pageContent

  def pageContent: Frag = frag()
}
