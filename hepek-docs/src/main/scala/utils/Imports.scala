package utils

import ba.sake.hepek.html.statik

import scalatags.Text.all.*

object Imports {
  val Section = statik.Section
  type Section = statik.Section

  // class field/method description
  case class ClassProperty(
      name: String,
      tpe: String,
      desc: String = "",
      defaultValue: Option[String] = None
  )

  def renderClassProps(props: List[ClassProperty]) =
    div(
      table(
        tr(th("Name"), th("Mandatory"), th("Type"), th("Default value"), th("Description")),
        props.map { cp =>
          tr(
            td(cp.name),
            td(cp.defaultValue.map(_ => "No").getOrElse("Yes")),
            td(cp.tpe),
            td(cp.defaultValue),
            td(cp.desc)
          )
        }
      )
    )
}
