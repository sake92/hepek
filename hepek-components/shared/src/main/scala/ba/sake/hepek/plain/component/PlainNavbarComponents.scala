package ba.sake.hepek.plain.component

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.NavbarComponents

object PlainNavbarComponents

// TODO this is just copied from Bootstrap...
case class PlainNavbarComponents() extends NavbarComponents {
  import ba.sake.hepek.plain.component.classes.PlainClassesBundle._

  private val bsBtn = tag("button")(tpe := "button", btnClass)

  def navbarCollapseId: String = "main-navbar"

  def toggle: ba.sake.hepek.scalatags.TypedTag[String] =
    bsBtn(
      cls := "nvbr-toggle collapsed",
      data.toggle := "collapse",
      data.target := s"#$navbarCollapseId"
    )(
      span(cls := "sr-only")("Toggle navigation"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar")
    )

  override def full(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    tag("nav")(cls := s"navbar")(
      div(cls := "container")(
        div(cls := "nvbr-header")(
          toggle,
          a(cls := "nvbr-brand", href := brandUrl)(
            brandIconUrl.map(url => span(img(src := url))),
            brandName
          )
        ),
        div(cls := "collapse nvbr-collapse", id := navbarCollapseId)(
          ul(cls := s"nav nvbr-nav nvbr-left")(
            left.map {
              case (item, liMods) =>
                li(liMods)(item)
            }
          ),
          ul(cls := s"nav nvbr-nav nvbr-right")(
            right.map {
              case (item, liMods) =>
                li(liMods)(item)
            }
          )
        )
      )
    )

  override def fullNestedLink(
      title: Frag,
      links: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    li(cls := "dropdown")(
      a(cls := "dropdown-toggle", data.toggle := "dropdown", href := "#")(
        title
      ),
      ul(cls := "dropdown-menu")(
        links.map {
          case (item, liMods) =>
            li(liMods)(item)
        }
      )
    )
}
