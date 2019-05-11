package ba.sake.hepek.pure.component

import ba.sake.hepek.html.component.ComponentsBundle

trait PureComponentsBundle extends ComponentsBundle {

  override type Grid = PureGridComponents
  override type Form = PureFormComponents
}
