package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}

case class BootstrapImageComponents() extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag = {
    val imgTag = img(
      src := source,
      cls := "img-responsive",
      all.alt := alt,
      all.width := width,
      all.height := height
    )
    if (title.trim.isEmpty)
      imgTag
    else
      div(cls := "thumbnail")(
        imgTag,
        div(cls := "caption", txtAlignCenter)(p(title))
      )
  }

  override def svg(source: String, title: String = "") = {
    val objectTag =
      tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
        "Problem with rendering SVG..."
      )
    if (title.trim.isEmpty)
      objectTag
    else
      div(cls := "thumbnail")(
        objectTag,
        div(cls := "caption", txtAlignCenter)(p(title))
      )
  }
}
