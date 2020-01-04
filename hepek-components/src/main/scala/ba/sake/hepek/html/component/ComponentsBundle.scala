package ba.sake.hepek.html.component

import ba.sake.hepek.html.HepekAliases
import ba.sake.hepek.html.component.classes.ClassesBundle

trait ComponentsBundle extends BasicComponents with HepekAliases {
  val Form: FormComponents
  val Grid: GridComponents
  //val Image: ImageComponents
  val Navbar: NavbarComponents
  val Panel: PanelComponents
  val Classes: ClassesBundle
}
