package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.BulmaPage
import ba.sake.hepek.bulma.component._
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component._

case class BulmaStaticBundle(
    Form: BulmaFormComponents = BulmaFormComponents.default,
    Grid: BulmaGridComponents = BulmaGridComponents.default,
    Image: PlainImageComponents = PlainImageComponents.default,
    Navbar: BulmaNavbarComponent = BulmaNavbarComponent.default,
    Panel: BulmaPanelComponents = BulmaPanelComponents.default,
    Classes: BulmaClassesBundle = BulmaClassesBundle
) extends StaticBundle
    with BulmaUtilComponents {

  override type Page = BulmaPage

  override type StaticPage = BulmaStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BulmaFormComponents): BulmaStaticBundle =
    copy(Form = Form)

  def withGrid(Grid: BulmaGridComponents): BulmaStaticBundle =
    copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): BulmaStaticBundle =
    copy(Image = Image)

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaStaticBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: BulmaPanelComponents): BulmaStaticBundle =
    copy(Panel = Panel)

  def withClasses(Classes: BulmaClassesBundle): BulmaStaticBundle =
    copy(Classes = Classes)
}
