package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.NavbarComponents
import ba.sake.hepek.scalatags.all, all.{style => _, Style => _, _}
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle.*
import BootstrapNavbarComponents.*

final class BootstrapNavbarComponents private (
    activeUrl: String = "",
    style: Style = Style.Default,
    width: Width = Width.Fluid,
    position: Position = Position.FixedTop,
    collapseId: String = "main-navbar"
) extends NavbarComponents {

  private val toggle =
    tag("button")(tpe := "button")(
      btnClass,
      cls         := "navbar-toggle collapsed",
      data.toggle := "collapse",
      data.target := s"#$collapseId"
    )(
      span(cls := "sr-only")("Toggle navigation"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar")
    )

  val Companion = BootstrapNavbarComponents

  def withActiveUrl(activeUrl: String): BootstrapNavbarComponents   = copy(activeUrl = activeUrl)
  def withStyle(style: Style): BootstrapNavbarComponents            = copy(style = style)
  def withWidth(width: Width): BootstrapNavbarComponents            = copy(width = width)
  def withPosition(position: Position): BootstrapNavbarComponents   = copy(position = position)
  def withCollapseId(collapseId: String): BootstrapNavbarComponents = copy(collapseId = collapseId)

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Frag = frag(),
      right: Frag = frag()
  ): Frag =
    tag("nav")(cls := s"navbar ${style.classes} ${position.classes}")(
      div(cls := width.classes)(
        div(cls := "navbar-header")(
          toggle,
          a(cls := "navbar-brand", href := brandUrl)(
            brandIconUrl.map(url =>
              span(img(src := url, alt := "logo", widthA := 32, heightA := 32))
            ),
            brandName
          )
        ),
        div(cls := "collapse navbar-collapse", id := collapseId)(
          ul(cls := s"nav navbar-nav navbar-left")(left),
          ul(cls := s"nav navbar-nav navbar-right")(right)
        )
      )
    )

  override def link(url: String, content: Frag): Frag =
    li(Option.when(url == activeUrl)(cls := "active"))(
      content
    )

  override def dropdown(
      content: Frag,
      dropdownItems: Seq[Frag] = Seq.empty
  ): Frag =
    li(cls := "dropdown")(
      a(cls := "dropdown-toggle", data.toggle := "dropdown", href := "#")(
        content,
        raw(" <span class='caret'></span>")
      ),
      ul(cls := "dropdown-menu")(dropdownItems)
    )

  private def copy(
      activeUrl: String = activeUrl,
      style: Style = style,
      width: Width = width,
      position: Position = position,
      collapseId: String = collapseId
  ) = new BootstrapNavbarComponents(activeUrl, style, width, position, collapseId)
}

object BootstrapNavbarComponents:

  val default: BootstrapNavbarComponents = new BootstrapNavbarComponents(
    activeUrl = "",
    Style.Default,
    Width.Fluid,
    Position.FixedTop,
    collapseId = "main-navbar"
  )

  enum Position(val classes: String):
    case FixedTop    extends Position("navbar-fixed-top")
    case FixedBottom extends Position("navbar-fixed-bottom")

  enum Width(val classes: String):
    case Fixed extends Width("container")
    case Fluid extends Width("container-fluid")

  enum Style(val classes: String):
    case Default extends Style("navbar-default")
    case Inverse extends Style("navbar-inverse")
