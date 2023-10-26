package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

case class PlainBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends Bundle with PlainUtilComponents {
  override type HtmlPage = PlainPage

  def withForm(Form: PlainFormComponents): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: PlainGridComponents): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: PlainImageComponents): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: PlainNavbarComponents): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: PlainPanelComponents): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: PlainClassesBundle): PlainBundle =
    new PlainBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
