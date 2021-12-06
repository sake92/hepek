package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.BulmaPage
import ba.sake.hepek.bulma.component._
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component._

case class BulmaStaticBundle(
    Form: BulmaFormComponents = BulmaFormComponents(),
    Grid: BulmaGridComponents = BulmaGridComponents(),
    Image: PlainImageComponents = PlainImageComponents(),
    Navbar: BulmaNavbarComponent = BulmaNavbarComponent(),
    Panel: BulmaPanelComponents = BulmaPanelComponents(),
    Classes: BulmaClassesBundle = BulmaClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with BulmaMarkdownComponents {
  override type HtmlPage   = BulmaPage
  override type StaticPage = BulmaStaticPage

  def withForm(Form: BulmaFormComponents): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: BulmaGridComponents): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: PlainImageComponents): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: BulmaPanelComponents): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: BulmaClassesBundle): BulmaStaticBundle =
    new BulmaStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
