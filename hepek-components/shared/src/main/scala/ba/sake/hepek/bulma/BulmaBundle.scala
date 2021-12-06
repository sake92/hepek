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
    with PlainLinkComponents {
  override type HtmlPage = BulmaPage

  def withForm(Form: BulmaFormComponents): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: BulmaGridComponents): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: PlainImageComponents): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: BulmaNavbarComponent): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: BulmaPanelComponents): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: BulmaClassesBundle): BulmaBundle =
    new BulmaBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
