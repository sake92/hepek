package ba.sake.hepek.bootstrap5

import ba.sake.hepek.bootstrap5.component._
import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.component.BootstrapPanelComponents

case class BootstrapBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(), // TODO replace with cards..
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends Bundle
    with BootstrapUtilComponents {

  override type Page = BootstrapPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BootstrapFormComponents): BootstrapBundle =
    copy(Form = Form)

  def withGrid(Grid: BootstrapGridComponents): BootstrapBundle =
    copy(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapBundle =
    copy(Image = Image)

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapBundle =
    copy(Navbar = Navbar)

  def withClasses(Classes: BootstrapClassesBundle): BootstrapBundle =
    copy(Classes = Classes)
}
