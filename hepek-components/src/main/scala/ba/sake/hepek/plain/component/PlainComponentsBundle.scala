package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

trait PlainComponentsBundle extends ComponentsBundle with PlainBasicComponents {
  override val Form    = PlainFormComponents()
  override val Grid    = PlainGridComponents()
  override val Navbar  = PlainNavbarComponents
  override val Panel   = PlainPanelComponents
  override val Classes = PlainClassesBundle
}
