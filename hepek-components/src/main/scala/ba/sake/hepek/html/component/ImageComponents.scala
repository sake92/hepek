package ba.sake.hepek.html.component

import scalatags.Text.all.{caption => _, _}

trait ImageComponents {

  /** Renders an image with optional caption text */
  def image(source: String, caption: String = ""): Frag =
    img(src := source, alt := caption)

  /** Renders an SVG image with optional caption text */
  def svg(source: String, caption: String = ""): Frag =
    tag("object")(tpe := "image/svg+xml", data := source)(
      "Problem with rendering SVG..."
    )
}
