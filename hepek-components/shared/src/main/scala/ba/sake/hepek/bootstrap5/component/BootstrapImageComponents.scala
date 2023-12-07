package ba.sake.hepek.bootstrap5.component

import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle.*
import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}

final class BootstrapImageComponents private () extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag = {
    val imgTag = img(
      src     := source,
      cls     := "img-responsive",
      all.alt := alt,
      widthA  := width,
      heightA := height
    )
    if (title.trim.isEmpty)
      imgTag
    else
      div(cls := "thumbnail")(
        imgTag,
        div(cls := "caption", txtAlignCenter)(title)
      )
  }

  override def svg(source: String, title: String = "") = {
    val objectTag =
      tag("object")(tpe := "image/svg+xml", data := source)(
        "Problem with rendering SVG..."
      )
    if (title.trim.isEmpty)
      objectTag
    else
      div(cls := "thumbnail")(
        objectTag,
        div(cls := "caption", txtAlignCenter)(title)
      )
  }
}

object BootstrapImageComponents:
  val default: BootstrapImageComponents = new BootstrapImageComponents()
