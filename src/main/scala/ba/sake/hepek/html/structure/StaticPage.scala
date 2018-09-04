package ba.sake.hepek.html.structure

import scalatags.Text.all._
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.path.ClassPackageRelativePath

trait StaticPage extends Renderable with ClassPackageRelativePath with PageDependencies {

  def siteSettings: SiteSettings = SiteSettings()

  def pageSettings: PageSettings = PageSettings()

  def renderPretty = false
  def renderXhtml  = false

  // <head>
  def headContent: List[Frag] =
    List(
      meta(charset := "utf-8"),
      meta(
        attr("http-equiv") := "X-UA-Compatible",
        content := "ie=edge"
      ),
      meta(
        name := "viewport",
        content := "width=device-width, initial-scale=1"
      ),
      pageSettings.description.map { d =>
        meta(name := "description", content := d)
      },
      tag("title")(
        pageSettings.title + siteSettings.name.map(n => " - " + n)
      ),
      siteSettings.faviconNormal.map { fav =>
        link(rel := "shortcut icon", href := fav, tpe := "image/x-icon")
      }
    )

  // <body>
  def bodyContent: List[Frag] = List.empty

  override def render: String = {
    // inline css
    val compStyleInlines = components.flatMap { _._2.cssDependencies.inlines }
    val allStyleInlines  = compStyleInlines ++ stylesInline
    // urls css
    val compStyleUrls = components.flatMap {
      case (cs, cd) =>
        cd.cssDependencies.urls ++
          cd.cssDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allStyleURLs = compStyleUrls ++ styleURLs

    // inline js
    val compScriptInlines = components.flatMap { _._2.jsDependencies.inlines }
    val allScriptInlines  = compScriptInlines ++ scriptsInline
    // urls js
    val compScriptUrls = components.flatMap {
      case (cs, cd) =>
        cd.jsDependencies.urls ++
          cd.jsDependencies.deps.map(cs.depsProvider.depPath)
    }
    val allScriptURLs = compScriptUrls ++ scriptURLs

    // CONTENT
    val rawContent = "<!DOCTYPE html>" +
      html(lang := pageSettings.language)(
        head(
          headContent ++
            allStyleURLs.map(u => link(rel := "stylesheet", href := u)) ++
            allStyleInlines.map(s => tag("style")(s))
        ),
        body(
          bodyContent ++
            allScriptURLs.map(u => script(src := u)) ++
            allScriptInlines.map(s => script(raw(s)))
        )
      )

    // optionally XHTML-ify and pretty-fy
    if (renderXhtml || renderPretty) {
      val document = org.jsoup.Jsoup.parse(rawContent)
      document
        .outputSettings()
        .prettyPrint(renderPretty)
      if (renderXhtml) {
        document
          .outputSettings()
          .syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml)
      }
      document.html
    } else {
      rawContent
    }
  }

}

/* website setttings */
case class SiteSettings(
    name: Option[String] = None,
    indexPage: Option[StaticPage] = None,
    mainPages: List[StaticPage] = List.empty,
    faviconNormal: Option[String] = None,
    faviconInverted: Option[String] = None
) {
  def withName(n: String)                      = copy(name = Some(n))
  def withName(n: Option[String])              = copy(name = n)
  def withIndexPage(ip: StaticPage)            = copy(indexPage = Some(ip))
  def withIndexPage(ip: Option[StaticPage])    = copy(indexPage = ip)
  def withMainPages(mps: List[StaticPage])     = copy(mainPages = mps)
  def withMainPages(mps: StaticPage*)          = copy(mainPages = mps.toList)
  def withFaviconNormal(fav: String)           = copy(faviconNormal = Some(fav))
  def withFaviconNormal(fav: Option[String])   = copy(faviconNormal = fav)
  def withFaviconInverted(fav: String)         = copy(faviconInverted = Some(fav))
  def withFaviconInverted(fav: Option[String]) = copy(faviconInverted = fav)
}

/* page setttings */
case class PageSettings(
    title: String = PageSettings.DefaultTitle,
    label: String = PageSettings.DefaultTitle,
    language: String = PageSettings.DefaultLanguage,
    category: Option[String] = None,
    description: Option[String] = None
) {
  def withTitle(t: String)               = copy(title = t)
  def withLabel(l: String)               = copy(label = l)
  def withLanguage(l: String)            = copy(language = l)
  def withCategory(c: String)            = copy(category = Some(c))
  def withCategory(c: Option[String])    = copy(category = c)
  def withDescription(d: String)         = copy(description = Some(d))
  def withDescription(d: Option[String]) = copy(description = d)
}

object PageSettings {
  val DefaultTitle    = "ChangeMe!"
  val DefaultLanguage = "en"
}
