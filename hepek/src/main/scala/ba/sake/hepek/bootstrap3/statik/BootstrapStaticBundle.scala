package ba.sake.hepek.bootstrap3.statik

import ba.sake.hepek.bootstrap3.BootstrapPage
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.plain.component.PlainMarkdownComponents

case class BootstrapStaticBundle(
    Form: BootstrapFormComponents = BootstrapFormComponents(),
    Grid: BootstrapGridComponents = BootstrapGridComponents(),
    Image: BootstrapImageComponents = BootstrapImageComponents(),
    Navbar: BootstrapNavbarComponents = BootstrapNavbarComponents(),
    Panel: BootstrapPanelComponents = BootstrapPanelComponents(),
    Classes: BootstrapClassesBundle = BootstrapClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with Bootstrap3MarkdownComponents {
  override type HtmlPage   = BootstrapPage
  override type StaticPage = BootstrapStaticPage

  def withForm(Form: BootstrapFormComponents): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: BootstrapGridComponents): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: BootstrapImageComponents): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: BootstrapNavbarComponents): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: BootstrapPanelComponents): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: BootstrapClassesBundle): BootstrapStaticBundle =
    new BootstrapStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
