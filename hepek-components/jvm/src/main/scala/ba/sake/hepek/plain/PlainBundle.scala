package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

case class PlainBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents.default,
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends Bundle
    with PlainUtilComponents {

  override type Page = PlainPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: PlainFormComponents): PlainBundle =
    copy(Form = Form)

  def withGrid(Grid: PlainGridComponents): PlainBundle =
    copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): PlainBundle =
    copy(Image = Image)

  def withNavbar(Navbar: PlainNavbarComponents): PlainBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: PlainPanelComponents): PlainBundle =
    copy(Panel = Panel)

  def withClasses(Classes: PlainClassesBundle): PlainBundle =
    copy(Classes = Classes)
}
