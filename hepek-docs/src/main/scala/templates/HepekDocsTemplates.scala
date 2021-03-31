package templates

import scalatags.Text.all._
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.prismjs
import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.theme.bootstrap3.{HepekBootstrap3BlogPage, TocSettings, TocType}
import utils.Imports.Bundle._
import utils.Imports._
import utils._
import resources._
import ba.sake.hepek.bootstrap3.component.BootstrapNavbarComponents
import ba.sake.hepek.html.ComponentDependencies
import scalatags.text.Builder

trait HepekDocsAbstractPage
    extends HepekBootstrap3BlogPage
    with HepekDocsStaticPage
    with prismjs.PrismDependencies {
  // dont have to remember ordering of these.. filter below!
  val hlLangs: List[String] = List("core", "clike", "scala", "java", "markup")

  override def prismSettings: prismjs.PrismSettings =
    super.prismSettings
      .withTheme(prismjs.Themes.Okaidia)
      .withLanguages(prismjs.PrismConsts.languages filter hlLangs.contains)

  override def tocSettings: Some[TocSettings] =
    Some(
      TocSettings(
        tocType = TocType.Scrollspy(45)
      )
    )

  override def pageHeader = None
}

trait HepekDocsStaticPage extends StaticPage with AnchorjsDependencies with FADependencies {

  override def siteSettings: SiteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(images.ico("favicon").ref)
      .withFaviconInverted(images.ico("favicon-small").ref)

  override def staticSiteSettings: StaticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(docs.Index)
      .withMainPages(docs.hepek.components.Index, docs.hepek.Index, docs.hepek.play.Index)

  override def navbar: Some[BootstrapNavbarComponents] = Some(Navbar)

  // CSS
  override def styleURLs: List[String] =
    super.styleURLs ++ List(styles.css("main").ref)

  override def bootstrapDependencies: ComponentDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies()
      .withDeps(Dependency("yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def scriptURLs: List[String] = super.scriptURLs :+ scripts.js("main").ref

  override def pageContent: Frag[Builder,String] = {
    import Classes._
    frag(
      super.pageContent,
      footer(txtAlignCenter, bgInfo, cls := "navbar-fixed-bottom")(
        hyperlink("https://github.com/sake92/hepek", btnClass)(
          FA.github()
        ),
        hyperlink("https://gitter.im/sake92/hepek", btnClass)(
          FA.gitter()
        )
      )
    )
  }
}
