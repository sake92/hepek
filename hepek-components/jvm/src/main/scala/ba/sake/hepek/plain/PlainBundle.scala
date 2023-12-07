package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component.*
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

final class PlainBundle private (
    val Form: PlainFormComponents,
    val Grid: PlainGridComponents,
    val Image: PlainImageComponents,
    val Navbar: PlainNavbarComponents,
    val Panel: PlainPanelComponents,
    val Classes: PlainClassesBundle
) extends Bundle
    with PlainUtilComponents {

  override type Page = PlainPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: PlainFormComponents): PlainBundle = copy(Form = Form)

  def withGrid(Grid: PlainGridComponents): PlainBundle = copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): PlainBundle = copy(Image = Image)

  def withNavbar(Navbar: PlainNavbarComponents): PlainBundle = copy(Navbar = Navbar)

  def withPanel(Panel: PlainPanelComponents): PlainBundle = copy(Panel = Panel)

  def withClasses(Classes: PlainClassesBundle): PlainBundle = copy(Classes = Classes)

  private def copy(
      Form: PlainFormComponents = Form,
      Grid: PlainGridComponents = Grid,
      Image: PlainImageComponents = Image,
      Navbar: PlainNavbarComponents = Navbar,
      Panel: PlainPanelComponents = Panel,
      Classes: PlainClassesBundle = Classes
  ) = new PlainBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object PlainBundle:
  val default: PlainBundle =
    new PlainBundle(
      PlainFormComponents.default,
      PlainGridComponents.default,
      PlainImageComponents.default,
      PlainNavbarComponents.default,
      PlainPanelComponents.default,
      PlainClassesBundle
    )
