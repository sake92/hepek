package ba.sake.hepek.bulma.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.plain.component.PlainNavbarComponents
import ba.sake.hepek.plain.component.PlainPanelComponents

trait BulmaComponentsBundle extends ComponentsBundle with BulmaBasicComponents {
  override val Form   = BulmaFormComponents
  override val Grid   = BulmaGridComponents()
  override val Navbar = PlainNavbarComponents
  override val Panel  = PlainPanelComponents

  override val Classes = BulmaClassesBundle
}
