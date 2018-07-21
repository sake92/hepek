package hepek.utils

import ba.sake.hepek.Resources
import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import scalatags.Text.all._

object Imports extends BasicComponents {

  object resources extends Resources {
    override def siteRootPath = "docs"
  }

  object chl extends PrismCodeHighlightComponents

  // FontAwesome 5 brand
  def faBrand(name: String) = i(cls := s"fab fa-$name")

  // class field/method description
  case class ClassProperty(
      name: String,
      tpe: String,
      desc: String = "",
      defaultValue: Option[String] = None
  )

  def renderClassProps(props: List[ClassProperty]) =
    table(cls := "table table-hover")(
      tr(th("Name"),
         th("Type"),
         th("Mandatory"),
         th("Default value"),
         th("Description")),
      props.map {
        case ClassProperty(name, tpe, desc, defaultValue) =>
          tr(
            td(name),
            td(tpe),
            td(if (defaultValue.isDefined) "No" else "Yes"),
            td(defaultValue),
            td(desc)
          )
      }
    )

}
