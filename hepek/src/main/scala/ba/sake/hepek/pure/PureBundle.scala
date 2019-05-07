package ba.sake.hepek.pure

import ba.sake.hepek.html.component.Bundle
import ba.sake.hepek.pure.component._
import ba.sake.hepek.pure.statik.PureStaticPage

trait PureBundle extends Bundle {

  override type Grid = PureGridComponents
  override type Form = PureFormComponents

  override type StaticPage = PureStaticPage
}
