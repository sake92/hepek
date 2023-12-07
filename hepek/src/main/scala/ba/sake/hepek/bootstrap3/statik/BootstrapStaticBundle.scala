package ba.sake.hepek.bootstrap3.statik

import ba.sake.hepek.bootstrap3.BootstrapPage
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.StaticBundle

case class BootstrapStaticBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents.default,
    Grid: BootstrapGridComponents = BootstrapGridComponents.default,
    Image: BootstrapImageComponents = BootstrapImageComponents.default,
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents.default,
    Panel: BootstrapPanelComponents = BootstrapPanelComponents.default,
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends StaticBundle
    with BootstrapUtilComponents {

  override type Page = BootstrapPage

  override type StaticPage = BootstrapStaticPage

  val Tags = ba.sake.hepek.scalatags.all

  def withForm(Form: BootstrapFormComponents): BootstrapStaticBundle =
    copy(Form = Form)

  def withGrid(Grid: BootstrapGridComponents): BootstrapStaticBundle =
    copy(Grid = Grid)

  def withImage(Image: BootstrapImageComponents): BootstrapStaticBundle =
    copy(Image = Image)

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapStaticBundle =
    copy(Navbar = Navbar)

  def withPanel(Panel: BootstrapPanelComponents): BootstrapStaticBundle =
    copy(Panel = Panel)

  def withClasses(Classes: BootstrapClassesBundle): BootstrapStaticBundle =
    copy(Classes = Classes)
}
