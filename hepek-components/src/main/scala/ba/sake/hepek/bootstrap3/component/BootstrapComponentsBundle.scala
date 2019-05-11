package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.ComponentsBundle

trait BootstrapComponentsBundle extends ComponentsBundle {

  override type Grid = BootstrapGridComponents
  override type Form = BootstrapFormComponents

  override val Form = BootstrapFormComponents
}
