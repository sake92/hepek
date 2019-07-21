package ba.sake.hepek.html

package component

trait ComponentsBundle {

  type Grid <: GridComponents
  type Form <: FormComponents
  type Image <: ImageComponents
  type Navbar <: NavbarComponents
  type Panel <: PanelComponents

  val Form: Form
}
