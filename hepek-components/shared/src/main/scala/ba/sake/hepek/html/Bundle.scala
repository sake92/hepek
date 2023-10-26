package ba.sake.hepek.html

import ba.sake.hepek.html.component._
import ba.sake.hepek.html.component.classes.ClassesBundle

trait Bundle extends HepekAliases {
  self: UtilComponents =>

  def Form: FormComponents
  def Grid: GridComponents
  def Image: ImageComponents
  def Navbar: NavbarComponents
  def Panel: PanelComponents
  def Classes: ClassesBundle

  type HtmlPage <: ba.sake.hepek.html.HtmlPage
}
