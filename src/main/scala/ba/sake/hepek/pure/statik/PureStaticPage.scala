package ba.sake.hepek.pure

package statik

import scalatags.Text.all._
import ba.sake.hepek.pure.PureDependencies
import ba.sake.hepek.html.structure.StaticPage

trait PureStaticPage extends StaticPage with PureDependencies {

  def pageContent: Frag = frag()

  def withBootstrapNavbar: Boolean = true

  override def bodyContent: List[Frag] = {
    val maybeNavbar = frag() // if (withBootstrapNavbar) navbarr else frag()
    List(
      div(
        maybeNavbar,
        pageContent
      )
    )
  }

  /* NAVBAR */
  /*private def navbarr =
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
 */
}
