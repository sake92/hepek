package ba.sake.hepek.bulma

import ba.sake.hepek.bulma.component._
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component._

case class BulmaBundle(
    Form: BulmaFormComponents = BulmaFormComponents(),
    Grid: BulmaGridComponents = BulmaGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: BulmaNavbarComponent = BulmaNavbarComponent(),
    Panel: BulmaPanelComponents = BulmaPanelComponents(),
    Classes: BulmaClassesBundle = BulmaClassesBundle
) extends Bundle
    with BulmaUtilComponents {

  override type Page = BulmaPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BulmaFormComponents): BulmaBundle =
    copy(Form = Form)

  def withGrid(Grid: BulmaGridComponents): BulmaBundle =
    copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): BulmaBundle =
    copy(Image = Image)

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: BulmaPanelComponents): BulmaBundle =
    copy(Panel = Panel)

  def withClasses(Classes: BulmaClassesBundle): BulmaBundle =
    copy(Classes = Classes)
}
