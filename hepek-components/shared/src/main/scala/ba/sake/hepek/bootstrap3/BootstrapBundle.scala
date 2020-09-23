package ba.sake.hepek.bootstrap3

import ba.sake.stone.Wither
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.plain.component.PlainLinkComponents

@Wither
case class BootstrapBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(),
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends Bundle
    with PlainLinkComponents {
  override type HtmlPage = BootstrapPage
}
