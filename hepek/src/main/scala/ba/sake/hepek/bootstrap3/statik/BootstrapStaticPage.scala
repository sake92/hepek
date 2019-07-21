package ba.sake.hepek.bootstrap3

package statik

import scalatags.Text.all._
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.bootstrap3.component.BootstrapNavbarComponents

trait BootstrapStaticPage extends StaticPage with BootstrapPage {

  def navbar: Option[BootstrapNavbarComponents] = None

  override def bodyContent: Frag =
    div(cls := bootstrapContainer)(
      maybeNavbar,
      pageContent
    )

  /* NAVBAR */
  private def maybeNavbar =
    navbar.map { bsNav =>
      bsNav.navbar(
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
    } yield a(href := page.ref)(labela) -> Seq(cls := klasa)
}
