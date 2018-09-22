package ba.sake.hepek.pure

package statik

import scalatags.Text.all._
import ba.sake.hepek.html.structure.StaticPage
import ba.sake.hepek.html.component.GridComponents._
import component.PureMenuComponents._

trait PureStaticPage extends StaticPage with PureDependencies {

  def withPureMenu: Boolean = true

  override def bodyContent =
    if (withPureMenu) {
      import grid._
      row(
        half1(sidebarMenu),
        half2(pageContent)
      )
    } else pageContent

  override def stylesInline = {
    val maybeInlineStyles = if (withPureMenu) List("""
      .pure-menu-vertical {
        display: inline-block;
      }
    """) else List()
    super.stylesInline ++ maybeInlineStyles
  }

  // grid for layout with sidebar
  private object grid extends component.PureGridComponents {
    override def screenRatios = super.screenRatios.copy(
      lg = Ratios(Ratio(1, 5), Ratio(1, 1, 1))
    )
  }

  /* SIDEBAR */
  private def sidebarMenu =
    menu()(
      menuHeading()(
        siteSettings.faviconInverted.map { fav =>
          span(img(src := fav))
        },
        siteSettings.name.map(n => " " + n)
      ),
      menuList()(
        sidebarMenuItems
      )
    )

  private def sidebarMenuItems: List[Frag] =
    for {
      page <- siteSettings.mainPages
      labela = page.pageSettings.category.getOrElse(page.pageSettings.label)
      klasa = {
        if (this.pageSettings.category.isEmpty) ""
        else if (page.pageSettings.category == this.pageSettings.category)
          "pure-menu-selected "
        else ""
      }
    } yield
      menuItem()(
        menuLink(page.ref)(labela)
      )

}
