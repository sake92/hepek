package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainPage
import ba.sake.hepek.plain.component.*
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

final class PlainStaticBundle private (
    val Form: PlainFormComponents,
    val Grid: PlainGridComponents,
    val Image: PlainImageComponents,
    val Navbar: PlainNavbarComponents,
    val Panel: PlainPanelComponents,
    val Classes: PlainClassesBundle
) extends StaticBundle {

  override type Page = PlainPage

  override type StaticPage = PlainStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: PlainFormComponents): PlainStaticBundle = copy(Form = Form)

  def withGrid(Grid: PlainGridComponents): PlainStaticBundle = copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): PlainStaticBundle = copy(Image = Image)

  def withNavbar(Navbar: PlainNavbarComponents): PlainStaticBundle = copy(Navbar = Navbar)

  def withPanel(Panel: PlainPanelComponents): PlainStaticBundle = copy(Panel = Panel)

  def withClasses(Classes: PlainClassesBundle): PlainStaticBundle = copy(Classes = Classes)

  private def copy(
      Form: PlainFormComponents = Form,
      Grid: PlainGridComponents = Grid,
      Image: PlainImageComponents = Image,
      Navbar: PlainNavbarComponents = Navbar,
      Panel: PlainPanelComponents = Panel,
      Classes: PlainClassesBundle = Classes
  ) = new PlainStaticBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object PlainStaticBundle:
  val default: PlainStaticBundle =
    new PlainStaticBundle(
      PlainFormComponents.default,
      PlainGridComponents.default,
      PlainImageComponents.default,
      PlainNavbarComponents.default,
      PlainPanelComponents.default,
      PlainClassesBundle
    )
