package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.component.Bundle
import ba.sake.hepek.bootstrap3.component._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage

trait BootstrapBundle extends Bundle {

  type Grid = BootstrapGridComponents
  type Form = BootstrapFormComponents

  type StatikPage = BootstrapStaticPage
}
