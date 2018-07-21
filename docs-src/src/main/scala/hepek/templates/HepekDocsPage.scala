package hepek.templates

import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.html.structure._
import ba.sake.hepek.theme.bootstrap3.HepekBootstrap3BlogPage
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component.BootstrapGridComponents
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.prismjs
import hepek.images.Images
import hepek.utils.Site
import hepek.utils.Imports._, resources._

trait HepekDocsPage
    extends HepekBootstrap3BlogPage
    with HepekDocsStaticPage
    with prismjs.PrismDependencies {

  val hlLangs = List("core", "clike", "scala", "java", "markup")

  override def prismTheme = prismjs.Themes.Okaidia
  override def prismJSDependencies = super.prismJSDependencies.filter { d =>
    if (d.contains("component")) {
      hlLangs.exists(l => d.contains(s"prism-$l."))
    } else {
      true
    }
  }

  override def categoryPosts = Site.pages

}

trait HepekDocsStaticPage
    extends BootstrapStaticPage
    with BootstrapGridComponents
    with AnchorjsDependencies {

  override def siteSettings = SiteSettings(
    Site.name,
    docs.Index,
    List(docs.Docs),
    Option(relTo(Images.favicon)),
    Option(relTo(Images.faviconSmall))
  )

  override def styleURLs = super.styleURLs ++ List(
    relTo(styles.css("main")),
    "https://use.fontawesome.com/releases/v5.0.12/css/all.css"
  )
  override def bootstrapCSSDependencies = List(
    DependencyProvider.cdnjs.depPath(
      Dependency("yeti/bootstrap.min.css", bootstrapVersion, "bootswatch")
    )
  )
  override def scriptURLs = super.scriptURLs :+ relTo(scripts.js("main"))

  override def screenRatios = super.screenRatios.copy(sm = None, xs = None)

  override def pageContent =
    frag(
      super.pageContent,
      footer(cls := "text-center")(
        hr,
        div(cls := "btn-group btn-group-lg")(
          hyperlink("https://github.com/sake92/hepek", cls := "btn")(
            faBrand("github")
          ),
          hyperlink("https://gitter.im/sake92/hepek", cls := "btn")(
            faBrand("gitter")
          )
        )
      )
    )

}
