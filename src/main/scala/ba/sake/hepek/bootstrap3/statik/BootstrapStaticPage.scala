package ba.sake.hepek.bootstrap3

package statik

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.component.AllBootstrapComponents
import ba.sake.hepek.bootstrap3.component.BootstrapNavbarComponent
import ba.sake.hepek.html.structure.StaticPage

trait BootstrapStaticPage
    extends StaticPage
    with BootstrapDependencies
    with BootstrapNavbarComponent {

  // avoid polluting user's namespace
  import AllBootstrapComponents._

  def pageContent: Frag = frag()

  def withBootstrapFluidContainer: Boolean = true
  def withBootstrapNavbar: Boolean         = true

  override def bodyContent: List[Frag] = {
    val containerClass =
      if (withBootstrapFluidContainer) "container-fluid" else "container"
    val maybeNavbar = if (withBootstrapNavbar) navbarr else frag()
    List(
      div(cls := containerClass)(
        maybeNavbar,
        pageContent
      )
    )
  }

  /* NAVBAR */
  private def navbarr =
    navbar(
      navbarHeader()(
        navbarCollapseToggleBtn(),
        navbarBrand(relTo(siteSettings.indexPage))(
          siteSettings.faviconInverted.map { fav =>
            span(img(src := fav))
          },
          " " + siteSettings.name
        )
      ),
      navbarCollapse()(navbarContent()(navbarLiTags))
    )

  private def navbarLiTags: List[Frag] =
    for {
      page <- siteSettings.mainPages
      labela = page.pageCategory.getOrElse(page.pageLabel)
      klasa = {
        if (this.pageCategory.isEmpty) ""
        else if (page.pageCategory == this.pageCategory) "active "
        else ""
      }
    } yield li(cls := klasa)(a(href := relTo(page))(labela))

}
