package ba.sake.hepek.pure.component

import ba.sake.hepek.html.component.ImageComponents
import ba.sake.hepek.scalatags.all, all.{caption => _, _}

trait PureImageComponents extends ImageComponents {

  override def image(
      source: String,
      width: Int,
      height: Int,
      title: String = "",
      alt: String = ""
  ): Frag =
    div(
      img(src := source, cls := "pure-img", all.alt := alt, widthA := width, heightA := height),
      if (title.trim.isEmpty) frag()
      else div(cls := "caption text-center")(p(title))
    )

  override def svg(source: String, title: String = "") =
    div(
      tag("object")(tpe := "image/svg+xml", cls := "embed-responsive-item", data := source)(
        "Problem with rendering SVG..."
      ),
      if (title.trim.isEmpty) frag()
      else div(cls := "caption text-center")(p(title))
    )
}
