package ba.sake.hepek.bulma.component

import ba.sake.hepek.html.component.ComponentsBundle

trait BulmaComponentsBundle extends ComponentsBundle {
  override type Grid = BulmaGridComponents
  override type Form = BulmaFormComponents

  override val Form = BulmaFormComponents
}
