package ba.sake.hepek.bootstrap3.statik

import ba.sake.hepek.bootstrap3.BootstrapPage
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.plain.component.PlainMarkdownComponents
import ba.sake.stone.Wither

@Wither
case class BootstrapStaticBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(),
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = BootstrapPage
  override type StaticPage = BootstrapStaticPage
}
