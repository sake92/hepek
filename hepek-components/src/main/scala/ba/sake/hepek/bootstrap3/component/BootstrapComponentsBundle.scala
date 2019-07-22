package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.ComponentsBundle

trait BootstrapComponentsBundle extends ComponentsBundle {

  override type Form   = BootstrapFormComponents
  override type Grid   = BootstrapGridComponents
  override type Image  = BootstrapImageComponents
  override type Navbar = BootstrapNavbarComponents
  override type Panel  = BootstrapPanelComponents

  override val Form   = BootstrapFormComponents
  override val Navbar = BootstrapNavbarComponents
  override val Panel  = BootstrapPanelComponents
}
