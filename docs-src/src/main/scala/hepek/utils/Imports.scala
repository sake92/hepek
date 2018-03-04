package hepek.utils

import ba.sake.hepek.Resources
import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import scalatags.Text.all._

object Imports extends BasicComponents {

  object chl extends PrismCodeHighlightComponents

  object resources extends Resources {
    override def siteRootPath = "docs"
  }

}
