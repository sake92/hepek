package hepek.utils

import ba.sake.hepek.Resources
import ba.sake.hepek.bootstrap3.component.AllBootstrapComponents
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import scalatags.Text.all._

object Imports extends AllBootstrapComponents {

  object chl extends PrismCodeHighlightComponents

  object resources extends Resources {
    override def siteRootPath = "docs"
  }

}
