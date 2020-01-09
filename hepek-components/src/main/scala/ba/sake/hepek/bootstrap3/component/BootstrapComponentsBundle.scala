package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.plain.component.PlainBasicComponents
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle

object BootstrapComponentsBundle extends BootstrapComponentsBundle

trait BootstrapComponentsBundle extends ComponentsBundle with PlainBasicComponents {
  override val Form    = BootstrapFormComponents()
  override val Grid    = BootstrapGridComponents()
  override val Image   = BootstrapImageComponents()
  override val Navbar  = BootstrapNavbarComponents()
  override val Panel   = BootstrapPanelComponents()
  override val Classes = BootstrapClassesBundle
}
