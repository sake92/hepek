package ba.sake.hepek.bootstrap5.statik

import ba.sake.hepek.bootstrap5.BootstrapPage
import ba.sake.hepek.bootstrap5.component.*
import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.bootstrap3.component.BootstrapPanelComponents

final class BootstrapStaticBundle private (
    val Form: BootstrapFormComponents,
    val Grid: BootstrapGridComponents,
    val Image: BootstrapImageComponents,
    val Navbar: BootstrapNavbarComponents,
    val Panel: BootstrapPanelComponents, // TODO replace with cards..
    val Classes: BootstrapClassesBundle
) extends StaticBundle {

  override type Page = BootstrapPage

  override type StaticPage = BootstrapStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BootstrapFormComponents): BootstrapStaticBundle = copy(Form = Form)

  def withGrid(Grid: BootstrapGridComponents): BootstrapStaticBundle = copy(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapStaticBundle = copy(Image = Image)

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapStaticBundle = copy(Navbar = Navbar)

  def withPanel(Panel: BootstrapPanelComponents): BootstrapStaticBundle = copy(Panel = Panel)

  def withClasses(Classes: BootstrapClassesBundle): BootstrapStaticBundle = copy(Classes = Classes)

  private def copy(
      Form: BootstrapFormComponents = Form,
      Grid: BootstrapGridComponents = Grid,
      Image: BootstrapImageComponents = Image,
      Navbar: BootstrapNavbarComponents = Navbar,
      Panel: BootstrapPanelComponents = Panel,
      Classes: BootstrapClassesBundle = Classes
  ) = new BootstrapStaticBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object BootstrapStaticBundle:
  val default: BootstrapStaticBundle =
    new BootstrapStaticBundle(
      BootstrapFormComponents.default,
      BootstrapGridComponents.default,
      BootstrapImageComponents.default,
      BootstrapNavbarComponents.default,
      BootstrapPanelComponents.default,
      BootstrapClassesBundle
    )
