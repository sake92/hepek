package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle
import ba.sake.stone.Wither

@Wither
case class PlainBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends Bundle
    with PlainLinkComponents {
  override type HtmlPage = PlainPage
}
