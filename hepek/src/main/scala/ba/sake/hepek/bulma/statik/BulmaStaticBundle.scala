package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.BulmaPage
import ba.sake.hepek.bulma.component.*
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component.*

final class BulmaStaticBundle private (
    val Form: BulmaFormComponents,
    val Grid: BulmaGridComponents,
    val Image: PlainImageComponents,
    val Navbar: BulmaNavbarComponent,
    val Panel: BulmaPanelComponents,
    val Classes: BulmaClassesBundle
) extends StaticBundle {

  override type Page = BulmaPage

  override type StaticPage = BulmaStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BulmaFormComponents): BulmaStaticBundle = copy(Form = Form)

  def withGrid(Grid: BulmaGridComponents): BulmaStaticBundle = copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): BulmaStaticBundle = copy(Image = Image)

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaStaticBundle = copy(Navbar = Navbar)

  def withPanel(Panel: BulmaPanelComponents): BulmaStaticBundle = copy(Panel = Panel)

  def withClasses(Classes: BulmaClassesBundle): BulmaStaticBundle = copy(Classes = Classes)

  private def copy(
      Form: BulmaFormComponents = Form,
      Grid: BulmaGridComponents = Grid,
      Image: PlainImageComponents = Image,
      Navbar: BulmaNavbarComponent = Navbar,
      Panel: BulmaPanelComponents = Panel,
      Classes: BulmaClassesBundle = Classes
  ) = new BulmaStaticBundle(Form, Grid, Image, Navbar, Panel, Classes)
}

object BulmaStaticBundle:
  val default: BulmaStaticBundle =
    new BulmaStaticBundle(
      BulmaFormComponents.default,
      BulmaGridComponents.default,
      PlainImageComponents.default,
      BulmaNavbarComponent.default,
      BulmaPanelComponents.default,
      BulmaClassesBundle
    )
