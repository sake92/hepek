package ba.sake.hepek.html

package component

// we mixin BasicComponents cause they should be globally available
// things like "".md, hyperlink etc.
trait ComponentsBundle { self: BasicComponents =>

  type Form <: FormComponents
  type Grid <: GridComponents
  type Image <: ImageComponents
  type Navbar <: NavbarComponents
  type Panel <: PanelComponents

  val Form: Form
  //val Image: ImageComponents
  val Navbar: Navbar
  val Panel: Panel
}
