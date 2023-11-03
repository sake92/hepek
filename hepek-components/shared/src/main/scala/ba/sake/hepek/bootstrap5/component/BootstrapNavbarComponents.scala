package ba.sake.hepek.bootstrap5.component

import ba.sake.hepek.html.component.NavbarComponents
import ba.sake.hepek.scalatags.all, all.{style => _, Style => _, _}

object BootstrapNavbarComponents {
  sealed trait Position { def classes: String }

  object Position {
    case object FixedTop    extends Position { def classes = "fixed-top"    }
    case object FixedBottom extends Position { def classes = "fixed-bottom" }
  }

  sealed trait Width { def classes: String }

  object Width {
    case object Fixed extends Width { def classes = "container"       }
    case object Fluid extends Width { def classes = "container-fluid" }
  }

}

final case class BootstrapNavbarComponents(
    width: BootstrapNavbarComponents.Width = BootstrapNavbarComponents.Width.Fluid,
    position: BootstrapNavbarComponents.Position = BootstrapNavbarComponents.Position.FixedTop,
    collapseId: String = "main-navbar"
) extends NavbarComponents {
  import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle._

  val Companion = BootstrapNavbarComponents

  def toggle = tag("button")(
    tpe            := "button",
    cls            := "navbar-toggler collapsed",
    data.bs.toggle := "collapse",
    data.bs.target := s"#$collapseId"
  )(
    span(cls := "navbar-toggler-icon")
  )

  override def full(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    tag("nav")(cls := s"navbar navbar-expand-lg navbar-dark bg-dark ${position.classes}")(
      div(cls := width.classes)(
        a(cls := "navbar-brand", href := brandUrl)(
          brandIconUrl.map(url =>
            span(img(src := url, alt := "logo", widthA := 32, heightA := 32))
          ),
          brandName
        ),
        toggle,
        div(cls := "collapse navbar-collapse", id := collapseId)(
          ul(cls := "navbar-nav")(
            left.map { case (item, liMods) =>
              li(cls := "nav-item")(liMods)(item)
            }
          ),
          ul(cls := "navbar-nav")(
            right.map { case (item, liMods) =>
              li(cls := "nav-item")(liMods)(item)
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
        title,
        raw(" <span class='caret'></span>")
      ),
      ul(cls := "dropdown-menu")(
        links.map { case (item, liMods) =>
          li(liMods)(item)
        }
      )
    )
}
