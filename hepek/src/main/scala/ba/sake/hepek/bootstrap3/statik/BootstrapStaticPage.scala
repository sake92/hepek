package ba.sake.hepek.bootstrap3

package statik

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.component.BootstrapNavbarComponent
import ba.sake.hepek.bootstrap3.component.BootstrapNavbarComponent.{Position, Style}
import ba.sake.hepek.html.statik.StaticPage

trait BootstrapStaticPage extends StaticPage with BootstrapDependencies {

  def bootstrapContainer: String = "container-fluid"

  def bootstrapNavbar: Option[(Position, Style)] =
    Some(Position.FixedTop, Style.Default)

  override def stylesInline = {
    // see https://getbootstrap.com/docs/3.3/components/#navbar-fixed-bottom
    val padding = bootstrapNavbar.map(_._1.stylesInline)
    super.stylesInline ++ padding.toList
  }

  override def bodyContent: Frag = {
    val maybeNavbar = bootstrapNavbar.map {
      case (p, s) =>
        navbarr(p, s)
    } getOrElse frag()
    div(cls := bootstrapContainer)(
      maybeNavbar,
      pageContent
    )
  }

  /* NAVBAR */
  private def navbarr(p: Position, s: Style) = {
    val navbarComponent = new BootstrapNavbarComponent {
      override def navbarStyle    = s
      override def navbarPosition = p
    }
    import navbarComponent._

    navbar(
      navbarHeader()(
        navbarCollapseToggleBtn(),
        staticSiteSettings.indexPage.map { iPage =>
          navbarBrand(iPage.ref)(
            siteSettings.faviconInverted.map { fav =>
              span(img(src := fav))
            },
            siteSettings.name.map(n => " " + n)
          )
        }
      ),
      navbarCollapse()(navbarContent()(navbarLiTags))
    )
  }

  private def navbarLiTags: List[Frag] =
    for {
      page <- staticSiteSettings.mainPages
      labela = pageCategory.getOrElse(page.pageSettings.label)
      klasa = {
        if (this.pageCategory.isEmpty) ""
        else if (pageCategory == this.pageCategory) "active "
        else ""
      }
    } yield li(cls := klasa)(a(href := page.ref)(labela))
}
