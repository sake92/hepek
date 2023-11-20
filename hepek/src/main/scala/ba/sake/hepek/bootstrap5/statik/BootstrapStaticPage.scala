package ba.sake.hepek.bootstrap5

package statik

import ba.sake.hepek.bootstrap5.component.BootstrapNavbarComponents
import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.scalatags.all._

trait BootstrapStaticPage extends StaticPage with BootstrapPage {

  def navbar: Option[BootstrapNavbarComponents] = None

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
      labela = page.pageCategory getOrElse page.pageSettings.label
      klasa = {
        if (this.pageCategory.isEmpty) ""
        else if (page.pageCategory == this.pageCategory) "active"
        else ""
      }
    } yield a(href := page.ref, cls := klasa, cls := "nav-link")(labela) -> Seq()
}
