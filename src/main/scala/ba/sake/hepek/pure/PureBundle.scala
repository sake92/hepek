package ba.sake.hepek.pure

import ba.sake.hepek.html.component.Bundle
import ba.sake.hepek.pure.component._
import ba.sake.hepek.pure.statik.PureStaticPage

trait PureBundle extends Bundle {

  type Grid = PureGridComponents
  type Form = PureFormComponents

  type StatikPage = PureStaticPage
}
