package ba.sake.hepek.plain.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.PlainPage
import ba.sake.hepek.plain.component._
import ba.sake.hepek.plain.component.classes.PlainClassesBundle
import ba.sake.kalem.Wither

@Wither
case class PlainStaticBundle(
    Form: PlainFormComponents = PlainFormComponents(),
    Grid: PlainGridComponents = PlainGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: PlainNavbarComponents = PlainNavbarComponents(),
    Panel: PlainPanelComponents = PlainPanelComponents(),
    Classes: PlainClassesBundle = PlainClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = PlainPage
  override type StaticPage = PlainStaticPage

  def withForm(Form: PlainFormComponents): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: PlainGridComponents): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: PlainImageComponents): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: PlainNavbarComponents): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: PlainPanelComponents): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: PlainClassesBundle): PlainStaticBundle =
    new PlainStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
