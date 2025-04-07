package ba.sake.hepek.bootstrap5

import ba.sake.hepek.bootstrap5.component.*
import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bootstrap3.component.BootstrapPanelComponents

final class BootstrapBundle private (
    val Form: BootstrapFormComponents,
    val Grid: BootstrapGridComponents,
    val Image: BootstrapImageComponents,
    val Navbar: BootstrapNavbarComponents,
    val Panel: BootstrapPanelComponents, // TODO replace with cards..
    val Classes: BootstrapClassesBundle
) extends Bundle {

  override type Page = BootstrapPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BootstrapFormComponents): BootstrapBundle = copy(Form = Form)

  def withGrid(Grid: BootstrapGridComponents): BootstrapBundle = copy(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapBundle = copy(Image = Image)

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapBundle = copy(Navbar = Navbar)

  def withPanel(Panel: BootstrapPanelComponents): BootstrapBundle = copy(Panel = Panel)

  def withClasses(Classes: BootstrapClassesBundle): BootstrapBundle = copy(Classes = Classes)

  private def copy(
      Form: BootstrapFormComponents = Form,
      Grid: BootstrapGridComponents = Grid,
      Image: BootstrapImageComponents = Image,
      Navbar: BootstrapNavbarComponents = Navbar,
      Panel: BootstrapPanelComponents = Panel,
      Classes: BootstrapClassesBundle = Classes
  ) = new BootstrapBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object BootstrapBundle:
  val default: BootstrapBundle =
    new BootstrapBundle(
      BootstrapFormComponents.default,
      BootstrapGridComponents.default,
      BootstrapImageComponents.default,
      BootstrapNavbarComponents.default,
      BootstrapPanelComponents.default,
      BootstrapClassesBundle
    )
