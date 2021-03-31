package ba.sake.hepek.pure.component

import ba.sake.hepek.scalatags.all.{caption => _, _}
import ba.sake.hepek.html.component.ImageComponents
import scalatags.text.Builder

trait PureImageComponents extends ImageComponents {

  override def image(source: String, caption: String = ""): Frag[Builder,String] =
    frag(
      img(src := source, cls := "pure-img", alt := caption), {
        if (caption.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(caption))
      }
    )

  override def svg(source: String, captionn: String = ""): Frag[Builder,String] =
    frag(
      tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
        "Problem with rendering SVG..."
      ), {
        if (captionn.trim.isEmpty) frag()
        else div(cls := "caption text-center")(p(captionn))
      }
    )
}
   )
}
