package ba.sake.hepek.html

import scalatags.Text.all._

trait HtmlPage extends PageDependencies {

  def siteSettings: SiteSettings = SiteSettings()

  def pageSettings: PageSettings = PageSettings()

  def contents: String = {
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
    "<!DOCTYPE html>" +
      html(lang := pageSettings.language)(
        head(
          headContent,
          allStyleURLs.map(u => link(rel := "stylesheet", href := u)) ++
            allStyleInlines.map(s => tag("style")(raw(s)))
        ),
        body(
          bodyContent,
          allScriptURLs.map(u => script(src := u)) ++
            allScriptInlines.map(s => script(raw(s)))
        )
      )
  }

  // <head>
  def headContent: Frag =
    frag(
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
        pageSettings.title + siteSettings.name.map(n => " - " + n).getOrElse("")
      ),
      siteSettings.faviconNormal.map { fav =>
        link(rel := "shortcut icon", href := fav, tpe := "image/x-icon")
      }
    )

  // <body>
  def bodyContent: Frag = pageContent

  def pageContent: Frag = frag()

}

case class SiteSettings(
    name: Option[String] = None,
    faviconNormal: Option[String] = None,
    faviconInverted: Option[String] = None
) {
  def withName(n: String)                      = copy(name = Some(n))
  def withName(n: Option[String])              = copy(name = n)
  def withFaviconNormal(fav: String)           = copy(faviconNormal = Some(fav))
  def withFaviconNormal(fav: Option[String])   = copy(faviconNormal = fav)
  def withFaviconInverted(fav: String)         = copy(faviconInverted = Some(fav))
  def withFaviconInverted(fav: Option[String]) = copy(faviconInverted = fav)
}

case class PageSettings(
    title: String,
    label: String,
    language: String,
    description: Option[String]
) {

  def withTitle(t: String) =
    if (label.isEmpty || label == PageSettings.DefaultTitle)
      copy(title = t, label = t) // set label also, if not set
    else copy(title = t)
  def withLabel(l: String)               = copy(label = l)
  def withLanguage(l: String)            = copy(language = l)
  def withDescription(d: String)         = copy(description = Some(d))
  def withDescription(d: Option[String]) = copy(description = d)
}

object PageSettings {
  val DefaultTitle    = "changeme"
  val DefaultLanguage = "en"

  def apply(title: String = DefaultTitle): PageSettings =
    PageSettings(title, title, DefaultLanguage, None)
}
