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

  // FontAwesome 5 brand
  def faBrand(name: String) = i(cls := s"fab fa-$name")

  // class field/method description
  case class ClassProperty(
      name: String,
      tpe: String,
      desc: String = "",
      defaultValue: Option[String] = None
  )

}
