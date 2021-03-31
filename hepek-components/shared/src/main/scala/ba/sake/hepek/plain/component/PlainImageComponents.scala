package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.plain.component.classes.PlainClassesBundle._
import ba.sake.hepek.scalatags.all.{caption => _, _}

// TODO copied from BS..
case class PlainImageComponents() extends ImageComponents {

  override def image(source: String, caption: String = "") =
    if (caption.trim.isEmpty)
      img(src := source, cls := "img-responsive", alt := caption)
    else
      div(cls := "thumbnail")(
        img(src := source, cls := "img-responsive", alt := caption),
        div(cls := "caption", txtAlignCenter)(p(caption))
      )

  override def svg(source: String, caption: String = "") =
    if (caption.trim.isEmpty)
      tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
        "Problem with rendering SVG..."
      )
    else
      div(cls := "thumbnail")(
        tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
          "Problem with rendering SVG..."
        ),
        div(cls := "caption", txtAlignCenter)(p(caption))
      )
}
