package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all.{style => _, Style => _, _}
import ba.sake.stone.Wither
import ba.sake.hepek.html.component.NavbarComponents

object BNC {
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

@Wither
case class BootstrapNavbarComponents(
    style: BNC.Style = BNC.Style.Default,
    position: BNC.Position = BNC.Position.FixedTop,
    collapseId: String = "main-navbar"
) extends NavbarComponents {
  import ba.sake.hepek.bootstrap3.component.classes.BootstrapButtonClasses._

  val Companion = BNC

  private val bsBtn = tag("button")(tpe := "button", btnClass)

  def toggle =
    bsBtn(
      cls := "navbar-toggle collapsed",
      data.toggle := "collapse",
      data.target := s"#$collapseId"
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
    tag("nav")(cls := s"navbar ${style.classes} ${position.classes}")(
      div(cls := "container-fluid")(
        div(cls := "navbar-header")(
          toggle,
          a(cls := "navbar-brand", href := brandUrl)(
            brandIconUrl.map(url => span(img(src := url))),
            brandName
          )
        ),
        div(cls := "collapse navbar-collapse", id := collapseId)(
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
