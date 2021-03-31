package ba.sake.hepek.w3css.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.plain.component.PlainMarkdownComponents
import ba.sake.hepek.w3css.W3CssPage
import ba.sake.hepek.w3css.component._
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle
import ba.sake.stone.Wither

@Wither
case class W3CssStaticBundle(
    Form: W3CssFormComponents = W3CssFormComponents(),
    Grid: W3CssGridComponents = W3CssGridComponents(),
    Image: W3CssImageComponents = W3CssImageComponents(),
    Navbar: W3CssNavbarComponents = W3CssNavbarComponents,
    Panel: W3CssPanelComponents = W3CssPanelComponents(),
    Classes: W3CssClassesBundle = W3CssClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = W3CssPage
  override type StaticPage = W3CssStaticPage
}
