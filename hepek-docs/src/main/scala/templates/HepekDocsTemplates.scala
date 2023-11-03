package templates

import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.prismjs
import ba.sake.hepek.theme.bootstrap5.HepekBootstrap5BlogPage
import ba.sake.hepek.theme.bootstrap5.TocSettings
import ba.sake.hepek.theme.bootstrap5.TocType
import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*
import resources.*

trait HepekDocsAbstractPage
    extends HepekBootstrap5BlogPage
    with HepekDocsStaticPage
    with prismjs.PrismDependencies {

  override def prismSettings =
    super.prismSettings
      .withTheme(prismjs.Themes.Okaidia)

  override def tocSettings =
    Some(TocSettings(tocType = TocType.Scrollspy(45)))

}

trait HepekDocsStaticPage extends StaticPage with AnchorjsDependencies with FADependencies {

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(images.ico("favicon").ref)
      .withFaviconInverted(images.ico("favicon-small").ref)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(docs.Index)
      .withMainPages(docs.hepek.components.Index, docs.hepek.Index)

  override def navbar = Some(Navbar)

  // CSS
  override def styleURLs =
    super.styleURLs ++ List(styles.css("main").ref)

  override def bootstrapDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies()
      .withDeps(Dependency("dist/yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def scriptURLs = super.scriptURLs.appended(scripts.js("main").ref)

  override def pageContent = {
    frag(
      super.pageContent,
      footer(Classes.txtAlignCenter, Classes.bgInfo, cls := "fixed-bottom")(
        a(href := "https://github.com/sake92/hepek", Classes.btnClass)(
          FA.github()
        ),
        a(href := "https://gitter.im/sake92/hepek", Classes.btnClass)(
          FA.gitter()
        )
      )
    )
  }
}
