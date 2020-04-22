package ba.sake.hepek.plain.statik

import ba.sake.stone.Wither
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.PlainBundle
import ba.sake.hepek.plain.PlainPage
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

@Wither
final case class PlainStaticBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = PlainPage
  override type StaticPage = PlainStaticPage
}
