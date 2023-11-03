package ba.sake.hepek.bootstrap5.statik

import ba.sake.hepek.bootstrap5.BootstrapPage
import ba.sake.hepek.bootstrap5.component._
import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.bootstrap3.component.BootstrapPanelComponents

case class BootstrapStaticBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(), // TODO replace with cards..
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

  def withClasses(Classes: BootstrapClassesBundle): BootstrapStaticBundle =
    copy(Classes = Classes)
}
