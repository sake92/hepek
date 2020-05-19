package ba.sake.hepek.w3css.component

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.NavbarComponents

object W3CssNavbarComponents extends W3CssNavbarComponents

trait W3CssNavbarComponents extends NavbarComponents {
  private val DefaultNestedLinksAttrs = List(cls := "w3-bar-item w3-button")

  override def full(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    div(cls := "w3-bar w3-light-grey")(
      a(cls := "w3-bar-item", href := brandUrl)(
        brandIconUrl.map(url => span(img(src := url))),
        brandName
      ),
      left.map {
        case (item, liMods) =>
          div(cls := "w3-bar-item", liMods)(item)
      },
      right.map {
        case (item, liMods) =>
          div(cls := "w3-bar-item w3-right", liMods)(item)
      }
    )

  override def fullNestedLink(
      title: Frag,
      links: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    div(cls := "w3-dropdown-hover")(
      button(cls := "w3-button")(title),
      div(cls := "w3-dropdown-content w3-bar-block w3-card")(
        links.map {
          case (item, liMods) =>
            div(DefaultNestedLinksAttrs ++ liMods)(item)
        }
      )
    )
}
