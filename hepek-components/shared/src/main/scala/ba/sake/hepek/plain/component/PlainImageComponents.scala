package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}

final class PlainImageComponents private () extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag =
    figure(
      img(src := source, all.alt := alt, widthA := width, heightA := height),
      figcaption(title)
    )

  override def svg(source: String, title: String = ""): Frag =
    tag("object")(tpe := "image/svg+xml", data := source)(
      "Problem with rendering SVG..."
    )
}

object PlainImageComponents:
  def apply(): PlainImageComponents = new PlainImageComponents()

  