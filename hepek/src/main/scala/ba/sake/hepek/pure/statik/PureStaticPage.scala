package ba.sake.hepek.pure

package statik

import ba.sake.hepek.html.component.GridComponents._
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.pure.component.PureGridComponents
import ba.sake.hepek.scalatags.all._

import component.PureMenuComponents._

trait PureStaticPage extends StaticPage with PurePage {

  // grid for layout with sidebar
  private val grid = locally {
    val Grid = PureGridComponents.default
    Grid.withScreenRatios(
      Grid.screenRatios.withLg(
        Ratios.default.withHalf(1, 5).withThird(1, 1, 1)
      )
    )
  }

  def withPureMenu: Boolean = true

  override def bodyContent =
    if (withPureMenu) {
      import grid._
      row(
        half(sidebarMenu),
        half(pageContent)
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
      page <- staticSiteSettings.mainPages
      labela = page.pageCategory.getOrElse(page.pageSettings.label)
      klasa = {
        if (this.pageCategory.isEmpty) ""
        else if (page.pageCategory == this.pageCategory)
          "pure-menu-selected"
        else ""
      }
    } yield menuItem()(
      menuLink(page.ref)(labela)
    )
}
