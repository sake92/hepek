package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.NavbarComponents

object BootstrapNavbarComponents extends BootstrapNavbarComponents {

  sealed trait Position { def classes: String }

  object Position {
    case object FixedTop    extends Position { def classes = "navbar-fixed-top"    }
    case object FixedBottom extends Position { def classes = "navbar-fixed-bottom" }
  }

  sealed trait Style { def classes: String }

  object Style {
    case object Default extends Style { def classes = "navbar-default" }
    case object Inverse extends Style { def classes = "navbar-inverse" }
  }
}

trait BootstrapNavbarComponents extends NavbarComponents {
  import BootstrapNavbarComponents._
  import ba.sake.hepek.bootstrap3.component.classes.BootstrapButtonClasses._

  private val bsBtn = tag("button")(tpe := "button", btnClass)

  def navbarCollapseId: String = "main-navbar"
  def navbarStyle: Style       = Style.Default
  def navbarPosition: Position = Position.FixedTop

  def toggle =
    bsBtn(
      cls := "navbar-toggle collapsed",
      data.toggle := "collapse",
      data.target := s"#$navbarCollapseId"
    )(
      span(cls := "sr-only")("Toggle navigation"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar"),
      span(cls := "icon-bar")
    )

  override def navbar(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    tag("nav")(cls := s"navbar ${navbarStyle.classes} ${navbarPosition.classes}")(
      div(cls := "container-fluid")(
        div(cls := "navbar-header")(
          toggle,
          a(cls := "navbar-brand", href := brandUrl)(
            brandIconUrl.map(url => span(img(src := url))),
            brandName
          )
        ),
        div(cls := "collapse navbar-collapse", id := navbarCollapseId)(
          ul(cls := s"nav navbar-nav navbar-left")(
            left.map {
              case (item, liMods) =>
                li(liMods)(item)
            }
          ),
          ul(cls := s"nav navbar-nav navbar-right")(
            right.map {
              case (item, liMods) =>
                li(liMods)(item)
            }
          )
        )
      )
    )

  override def navbarNestedLink(
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
