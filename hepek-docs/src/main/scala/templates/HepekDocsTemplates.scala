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
import ba.sake.hepek.Resource

// HepekDocsAbstractBlogPage
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
      .withFaviconNormal(Resource("images/favicon.ico").ref)
      .withFaviconInverted(Resource("images/favicon.ico").ref)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.Index)
      .withMainPages(files.hepek.components.Index, files.hepek.Index)

  override def navbar = Some(Navbar)
  
  // TODO nested tutorijali, howtos itd
  /* 
  override def bodyContent: Frag = frag(
    maybeNavbar,
    div(clsContainerFluid)(pageContent)
  )

  /* NAVBAR */
  private def maybeNavbar =
    navbar.map { bsNav =>
      bsNav.full(
        brandUrl = staticSiteSettings.indexPage.map(_.ref).getOrElse("#"),
        brandName = siteSettings.name.map(" " + _),
        brandIconUrl = siteSettings.faviconInverted,
        right = navbarLiTags
      )
    }

  private def navbarLiTags: Seq[(Frag, Seq[AttrPair])] =
    for {
      page <- staticSiteSettings.mainPages
      labela      = page.pageCategory.getOrElse(page.pageSettings.label)
      maybeActive = Option.when(page.pageCategory == this.pageCategory)(cls := "active")
    } yield a(href := page.ref, maybeActive, cls := "nav-link")(labela) -> Seq()
   */

  // CSS
  override def styleURLs =
    super.styleURLs ++ List(Resource("styles/main.css").ref)

  override def bootstrapDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies()
      .withDeps(Dependency("dist/yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def scriptURLs = super.scriptURLs
    .appended(Resource("scripts/main.js").ref)

  override def pageContent =
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
