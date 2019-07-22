package ba.sake.hepek.html

package component

trait ComponentsBundle {

  type Form <: FormComponents
  type Grid <: GridComponents
  type Image <: ImageComponents
  type Navbar <: NavbarComponents
  type Panel <: PanelComponents

  val Form: Form
  val Navbar: Navbar
  val Panel: Panel
}
