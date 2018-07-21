package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all.{caption => _, _}

object BootstrapImageComponents extends BootstrapImageComponents

trait BootstrapImageComponents {

  /** Renders a pretty image with optional caption text */
  def image(source: String, caption: String = "") =
    div(cls := "thumbnail")(
      img(src := source, cls := "img-responsive", alt := caption), {
        if (caption.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(caption))
      }
    )

  /** Renders a pretty bootstrapy SVG image with optional caption text */
  def svg(source: String, captionn: String = "") =
    div(cls := "thumbnail")(
      tag("object")(tpe := "image/svg+xml",
                    cls := "embed-responsive-item",
                    data := source)("Problem with rendering SVG..."), {
        if (captionn.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(captionn))
      }
    )
}
