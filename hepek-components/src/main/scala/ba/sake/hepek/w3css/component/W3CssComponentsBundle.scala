package ba.sake.hepek.w3css.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.plain.component.PlainBasicComponents

trait W3CssComponentsBundle extends ComponentsBundle with PlainBasicComponents {
  override type Form   = W3CssFormComponents
  override type Grid   = W3CssGridComponents
  override type Image  = W3CssImageComponents
  override type Navbar = W3CssNavbarComponents
  override type Panel  = W3CssPanelComponents

  override val Form   = W3CssFormComponents
  override val Navbar = W3CssNavbarComponents
  override val Panel  = W3CssPanelComponents
}
