package ba.sake.hepek.pure

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.pure.component.PureComponentsBundle

trait PureBundle extends Bundle with PureComponentsBundle {

  override type HtmlPage = PurePage
}
