package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.ComponentsBundle

trait PlainComponentsBundle extends ComponentsBundle {
  override type Form   = PlainFormComponents
  override type Grid   = PlainGridComponents
  override type Image  = PlainImageComponents
  override type Navbar = PlainNavbarComponents
  override type Panel  = PlainPanelComponents

  override val Form   = PlainFormComponents
  override val Navbar = PlainNavbarComponents
  override val Panel  = PlainPanelComponents
}
