package ba.sake.hepek.pure.component

import scalatags.Text.all.{caption => _, _}

object PureImageComponents extends PureImageComponents

trait PureImageComponents {

  /** Renders a pretty image with optional caption text */
  def image(source: String, caption: String = "") =
    frag(
      img(src := source, cls := "pure-img", alt := caption), {
        if (caption.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(caption))
      }
    )

  /** Renders a pretty bootstrapy SVG image with optional caption text */
  def svg(source: String, captionn: String = "") =
    frag(
      tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
        "Problem with rendering SVG..."
      ), {
        if (captionn.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(captionn))
      }
    )
}
