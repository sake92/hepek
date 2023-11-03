package ba.sake.hepek.bootstrap3

import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.Bundle

case class BootstrapBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(),
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends Bundle
    with BootstrapUtilComponents {

  override type Page = BootstrapPage

  def withForm(Form: BootstrapFormComponents): BootstrapBundle =
    copy(Form = Form)

  def withGrid(Grid: BootstrapGridComponents): BootstrapBundle =
    copy(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapBundle =
    copy(Image = Image)

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: BootstrapPanelComponents): BootstrapBundle =
    copy(Panel = Panel)

  def withClasses(Classes: BootstrapClassesBundle): BootstrapBundle =
    copy(Classes = Classes)
}
