package ba.sake.hepek.bulma.statik

import ba.sake.stone.Wither
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bulma.BulmaBundle
import ba.sake.hepek.bulma.BulmaPage
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.bulma.component._
import ba.sake.hepek.plain.component._

@Wither
case class BulmaStaticBundle(
    Form: BulmaFormComponents = BulmaFormComponents(),
    Grid: BulmaGridComponents = BulmaGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: BulmaNavbarComponent = BulmaNavbarComponent(),
    Panel: BulmaPanelComponents = BulmaPanelComponents(),
    Classes: BulmaClassesBundle = BulmaClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with BulmaMarkdownComponents {
  override type HtmlPage   = BulmaPage
  override type StaticPage = BulmaStaticPage
}
