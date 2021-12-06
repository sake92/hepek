package ba.sake.hepek.w3css.statik

import ba.sake.hepek.html.StaticBundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.plain.component.PlainMarkdownComponents
import ba.sake.hepek.w3css.W3CssPage
import ba.sake.hepek.w3css.component._
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle

case class W3CssStaticBundle(
    Form: W3CssFormComponents = W3CssFormComponents(),
    Grid: W3CssGridComponents = W3CssGridComponents(),
    Image: W3CssImageComponents = W3CssImageComponents(),
    Navbar: W3CssNavbarComponents = W3CssNavbarComponents,
    Panel: W3CssPanelComponents = W3CssPanelComponents(),
    Classes: W3CssClassesBundle = W3CssClassesBundle
) extends StaticBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override type HtmlPage   = W3CssPage
  override type StaticPage = W3CssStaticPage

  def withForm(Form: W3CssFormComponents): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withGrid(Grid: W3CssGridComponents): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withImage(Image: W3CssImageComponents): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withNavbar(Navbar: W3CssNavbarComponents): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withPanel(Panel: W3CssPanelComponents): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )

  def withClasses(Classes: W3CssClassesBundle): W3CssStaticBundle =
    new W3CssStaticBundle(
      Form = Form,
      Grid = Grid,
      Image = Image,
      Navbar = Navbar,
      Panel = Panel,
      Classes = Classes
    )
}
