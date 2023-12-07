package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainPage
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

case class PlainStaticBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents.default,
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends StaticBundle
    with PlainUtilComponents {

  override type Page = PlainPage

  override type StaticPage = PlainStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: PlainFormComponents): PlainStaticBundle =
    copy(Form = Form)

  def withGrid(Grid: PlainGridComponents): PlainStaticBundle =
    copy(Grid = Grid)

  def withImage(Image: PlainImageComponents): PlainStaticBundle =
    copy(Image = Image)

  def withNavbar(Navbar: PlainNavbarComponents): PlainStaticBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: PlainPanelComponents): PlainStaticBundle =
    copy(Panel = Panel)

  def withClasses(Classes: PlainClassesBundle): PlainStaticBundle =
    copy(Classes = Classes)
}
