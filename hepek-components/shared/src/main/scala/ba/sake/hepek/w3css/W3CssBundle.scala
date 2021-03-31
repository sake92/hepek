package ba.sake.hepek.w3css

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.w3css.component._
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle
import ba.sake.stone.Wither

@Wither
case class W3CssBundle(
    Form: W3CssFormComponents = W3CssFormComponents(),
    Grid: W3CssGridComponents = W3CssGridComponents(),
    Image: W3CssImageComponents = W3CssImageComponents(),
    Navbar: W3CssNavbarComponents = W3CssNavbarComponents,
    Panel: W3CssPanelComponents = W3CssPanelComponents(),
    Classes: W3CssClassesBundle = W3CssClassesBundle
) extends Bundle
    with PlainLinkComponents {
  override type HtmlPage = W3CssPage
}
