package ba.sake.hepek.w3css.component

import scalatags.Text.all.{caption => _, _}
import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle._

case class W3CssImageComponents() extends ImageComponents {

  override def image(source: String, caption: String = "") =
    if (caption.trim.isEmpty)
      img(src := source, cls := "w3-border", alt := caption)
    else
      div(
        img(src := source, cls := "w3-border", alt := caption),
        div(cls := "w3-container", txtAlignCenter)(p(caption))
      )

  override def svg(source: String, caption: String = "") =
    if (caption.trim.isEmpty)
      tag("object")(tpe := "image/svg+xml", data := source)(
        "Problem with rendering SVG..."
      )
    else
      div(
        tag("object")(tpe := "image/svg+xml", data := source)(
          "Problem with rendering SVG..."
        ),
        div(cls := "w3-container", txtAlignCenter)(p(caption))
      )
}
