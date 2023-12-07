package ba.sake.hepek.bulma.component

import ba.sake.hepek.html.component.NavbarComponents
import ba.sake.hepek.scalatags.all.{style => _, Style => _, _}
import BulmaNavbarComponent.*

final class BulmaNavbarComponent private (
    activeUrl: String = "",
    style: Option[Style] = None,
    position: Option[Position] = Some(Position.FixedTop),
    collapseId: String = "main-navbar"
) extends NavbarComponents {

  private val toggle =
    a(
      cls         := "navbar-burger burger",
      data.target := collapseId,
      onclick     := "document.querySelector('.navbar-menu').classList.toggle('is-active');"
      // ugly hack: https://github.com/jgthms/bulma/issues/856#issuecomment-502072770
    )(
      span(),
      span(),
      span()
    )

  val Companion = BulmaNavbarComponent

  def withActiveUrl(activeUrl: String): BulmaNavbarComponent         = copy(activeUrl = activeUrl)
  def withStyle(style: Option[Style]): BulmaNavbarComponent          = copy(style = style)
  def withStyle(style: Style): BulmaNavbarComponent                  = withStyle(Some(style))
  def withPosition(position: Option[Position]): BulmaNavbarComponent = copy(position = position)
  def withPosition(position: Position): BulmaNavbarComponent   = copy(position = Some(position))
  def withCollapseId(collapseId: String): BulmaNavbarComponent = copy(collapseId = collapseId)

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[Frag] = Seq.empty,
      right: Seq[Frag] = Seq.empty
  ): Frag =
    tag("nav")(cls := s"""navbar ${style
      .map(_.classes)
      .getOrElse("")} ${position.map(_.classes).getOrElse("")}""")(
      div(cls := "navbar-brand")(
        a(cls := "navbar-item", href := brandUrl)(
          brandIconUrl.map(url => img(src := url)),
          brandName
        ),
        toggle
      ),
      div(cls := "navbar-menu", id := collapseId)(
        div(cls := "navbar-start")(left),
        div(cls := "navbar-end")(right)
      )
    )

  override def link(url: String, content: Frag): Frag =
    div(cls := "navbar-item", Option.when(url == activeUrl)(cls := "active"))(content)

  override def dropdown(
      content: Frag,
      dropdownItems: Seq[Frag] = Seq.empty
  ): Frag =
    div(cls := "navbar-item has-dropdown is-hoverable")(
      a(cls := "navbar-link")(content),
      div(cls := "navbar-dropdown")(
        dropdownItems
      )
    )

  private def copy(
      activeUrl: String = activeUrl,
      style: Option[Style] = style,
      position: Option[Position] = position,
      collapseId: String = collapseId
  ) = new BulmaNavbarComponent(activeUrl, style, position, collapseId)

}

object BulmaNavbarComponent:

  val default: BulmaNavbarComponent = new BulmaNavbarComponent(
    activeUrl = "",
    None,
    Some(Position.FixedTop),
    collapseId = "main-navbar"
  )

  enum Position(val classes: String):
    case FixedTop    extends Position("is-fixed-top")
    case FixedBottom extends Position("is-fixed-bottom")

  enum Width(val classes: String):
    case Fixed extends Width("container")
    case Fluid extends Width("container-fluid")

  final class Style private (val classes: String)
