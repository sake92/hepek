package ba.sake.hepek.bulma

import ba.sake.stone.Wither
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.bulma.component._
import ba.sake.hepek.plain.component._

@Wither
final case class BulmaBundle(
    Form: BulmaFormComponents = BulmaFormComponents(),
    Grid: BulmaGridComponents = BulmaGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: BulmaNavbarComponent = BulmaNavbarComponent(),
    Panel: BulmaPanelComponents = BulmaPanelComponents(),
    Classes: BulmaClassesBundle = BulmaClassesBundle
) extends Bundle
    with PlainLinkComponents {
  override type HtmlPage = BulmaPage
}
