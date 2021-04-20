package ba.sake.hepek.w3css.component

import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle._

case class W3CssImageComponents() extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag = {
    val imgTag = img(src := source, cls := "w3-border", all.alt := alt,widthA := width,
      heightA := height)
    if (title.trim.isEmpty) imgTag
    else
      div(
        imgTag,
        div(cls := "w3-container", txtAlignCenter)(p(title))
      )
  }

  override def svg(source: String, title: String = "") =
    if (title.trim.isEmpty)
      tag("object")(tpe := "image/svg+xml", data := source)(
        "Problem with rendering SVG..."
      )
    else
      div(
        tag("object")(tpe := "image/svg+xml", data := source)(
          "Problem with rendering SVG..."
        ),
        div(cls := "w3-container", txtAlignCenter)(p(title))
      )
}
