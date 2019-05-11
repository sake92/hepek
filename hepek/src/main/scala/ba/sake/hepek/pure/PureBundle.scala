package ba.sake.hepek.pure

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.pure.component.PureComponentsBundle
import ba.sake.hepek.pure.statik.PureStaticPage

trait PureBundle extends Bundle with PureComponentsBundle {

  override type StaticPage = PureStaticPage
}
