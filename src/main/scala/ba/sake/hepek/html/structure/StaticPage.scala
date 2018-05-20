package ba.sake.hepek.html.structure

import scalatags.Text.all._
import ba.sake.hepek.core.Renderable
import ba.sake.hepek.path.ClassPackageRelativePath

trait StaticPage
    extends Renderable
    with ClassPackageRelativePath
    with PageDependencies {

  def siteSettings: SiteSettings

  def pageTitle: String
  def pageLabel: String               = pageTitle
  def pageLanguage: String            = "en"
  def pageCategory: Option[String]    = None
  def pageDescription: Option[String] = None

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
      pageDescription.map { d =>
        meta(name := "description", content := d)
      },
      tag("title")(
        pageTitle + " - " + siteSettings.name
      ),
      siteSettings.faviconNormal.map { fav =>
        link(rel := "shortcut icon", href := fav, tpe := "image/x-icon")
      }
    )

  // <body>
  def bodyContent: List[Frag] = List.empty

  override def render: String = {
    val rawContent = "<!DOCTYPE html>" +
      html(lang := pageLanguage)(
        head(
          headContent ++
            styleURLs.map(u => link(rel := "stylesheet", href := u)) ++
            stylesInline.map(s => tag("style")(s))
        ),
        body(
          bodyContent ++
            scriptURLs.map(u => script(src := u)) ++
            scriptsInline.map(s => script(raw(s)))
        )
      )
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

case class SiteSettings(
    name: String,
    indexPage: StaticPage,
    mainPages: List[StaticPage] = List.empty,
    faviconNormal: Option[String] = None,
    faviconInverted: Option[String] = None
)
