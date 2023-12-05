package ba.sake.hepek.plain

package statik

import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.plain.component.PlainNavbarComponents
import ba.sake.hepek.plain.component.classes.PlainClassesBundle._
import ba.sake.hepek.scalatags.all._

trait PlainStaticPage extends StaticPage with PlainPage {
  def navbar: Option[PlainNavbarComponents] = None

  override def bodyContent: Frag =
    div(
      div(clsNoPrint)(maybeNavbar),
      pageContent
    )

  /* NAVBAR */
  private def maybeNavbar =
    navbar.map { nav =>
      nav.nav(
        brandUrl = staticSiteSettings.indexPage.map(_.ref).getOrElse("#"),
        brandName = siteSettings.name.map(" " + _),
        brandIconUrl = siteSettings.faviconInverted,
        right = for
          page <- staticSiteSettings.mainPages
          labela = page.pageCategory.getOrElse(page.pageSettings.label)
        yield nav.link(page.ref, labela)
      )
    }

}
