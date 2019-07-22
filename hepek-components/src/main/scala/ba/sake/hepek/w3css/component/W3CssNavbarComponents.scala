package ba.sake.hepek.w3css.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.NavbarComponents

object W3CssNavbarComponents extends W3CssNavbarComponents

trait W3CssNavbarComponents extends NavbarComponents {

  override def navbar(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    div(cls := "w3-bar")(
      div(
        a(href := brandUrl)(
          brandIconUrl.map(url => span(img(src := url))),
          brandName
        )
      ),
      ul(cls := s"nav navbar-nav navbar-left")(
        left.map {
          case (item, liMods) =>
            li(cls := "w3-bar-item", liMods)(item)
        }
      ),
      ul(cls := s"w3-right")(
        right.map {
          case (item, liMods) =>
            li(cls := "w3-bar-item", liMods)(item)
        }
      )
    )

}
