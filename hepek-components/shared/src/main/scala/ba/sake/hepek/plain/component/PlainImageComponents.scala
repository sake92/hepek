package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}

case class PlainImageComponents() extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag =
    figure(
      img(src := source, all.alt := alt, all.width := width, all.height := height),
      figcaption(title)
    )

  override def svg(source: String, title: String = ""): Frag =
    tag("object")(tpe := "image/svg+xml", data := source)(
      "Problem with rendering SVG..."
    )
}
