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
    new BootstrapBundle(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapBundle =
    new BootstrapBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapBundle =
    new BootstrapBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: BootstrapPanelComponents): BootstrapBundle =
    new BootstrapBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: BootstrapClassesBundle): BootstrapBundle =
    new BootstrapBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
