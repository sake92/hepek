package ba.sake.hepek.bulma.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle
import ba.sake.hepek.plain.component._

trait BulmaComponentsBundle extends ComponentsBundle with BulmaBasicComponents {
  override val Form    = BulmaFormComponents()
  override val Grid    = BulmaGridComponents()
  override val Image   = PlainImageComponents()
  override val Navbar  = PlainNavbarComponents()
  override val Panel   = PlainPanelComponents()
  override val Classes = BulmaClassesBundle
}
