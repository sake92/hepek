package ba.sake.hepek.plain.component

import ba.sake.hepek.html.component.NavbarComponents
import ba.sake.hepek.scalatags.all.*
import ba.sake.hepek.plain.component.classes.PlainClassesBundle.*

// this is just copied from Bootstrap...
final class PlainNavbarComponents private (activeUrl: String) extends NavbarComponents {

  private val navbarCollapseId: String = "main-navbar"

  private val toggle =
    tag("button")(
      tpe := "button",
      btnClass,
      cls         := "nvbr-toggle collapsed",
      data.toggle := "collapse",
      data.target := s"#$navbarCollapseId"
    )(
      span(cls := "sr-only")("Toggle navigation"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar")
    )

  def withActiveUrl(activeUrl: String) = new PlainNavbarComponents(activeUrl = activeUrl)

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Frag = frag(),
      right: Frag = frag()
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
            left
          ),
          ul(cls := s"nav nvbr-nav nvbr-right")(
            right
          )
        )
      )
    )

  override def link(url: String, content: Frag): Frag =
    li(a(href := url)(content))

  override def dropdown(
      content: Frag,
      dropdownItems: Seq[Frag] = Seq.empty
  ): Frag =
    li(cls := "dropdown")(
      a(cls := "dropdown-toggle", data.toggle := "dropdown", href := "#")(
        content
      ),
      ul(cls := "dropdown-menu")(
        dropdownItems
      )
    )
}

object PlainNavbarComponents:

  val default: PlainNavbarComponents = new PlainNavbarComponents(activeUrl = "")
