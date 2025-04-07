package ba.sake.hepek.bulma

import ba.sake.hepek.bulma.component.*
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component.*

final class BulmaBundle private (
    val Form: BulmaFormComponents,
    val Grid: BulmaGridComponents,
    val Image: PlainImageComponents,
    val Navbar: BulmaNavbarComponent,
    val Panel: BulmaPanelComponents,
    val Classes: BulmaClassesBundle
) extends Bundle {

  override type Page = BulmaPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BulmaFormComponents): BulmaBundle = copy(Form = Form)

  def withGrid(Grid: BulmaGridComponents): BulmaBundle = copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): BulmaBundle = copy(Image = Image)

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaBundle = copy(Navbar = Navbar)

  def withPanel(Panel: BulmaPanelComponents): BulmaBundle = copy(Panel = Panel)

  def withClasses(Classes: BulmaClassesBundle): BulmaBundle = copy(Classes = Classes)

  private def copy(
      Form: BulmaFormComponents = Form,
      Grid: BulmaGridComponents = Grid,
      Image: PlainImageComponents = Image,
      Navbar: BulmaNavbarComponent = Navbar,
      Panel: BulmaPanelComponents = Panel,
      Classes: BulmaClassesBundle = Classes
  ) = new BulmaBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object BulmaBundle:
  val default: BulmaBundle =
    new BulmaBundle(
      BulmaFormComponents.default,
      BulmaGridComponents.default,
      PlainImageComponents.default,
      BulmaNavbarComponent.default,
      BulmaPanelComponents.default,
      BulmaClassesBundle
    )
