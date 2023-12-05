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

class BootstrapNavbarComponents(
    activeUrl: String = "",
    width: BootstrapNavbarComponents.Width = BootstrapNavbarComponents.Width.Fluid,
    position: BootstrapNavbarComponents.Position = BootstrapNavbarComponents.Position.FixedTop,
    collapseId: String = "main-navbar"
) extends NavbarComponents {

  private val toggle = tag("button")(
    tpe            := "button",
    cls            := "navbar-toggler collapsed",
    data.bs.toggle := "collapse",
    data.bs.target := s"#$collapseId"
  )(
    span(cls := "navbar-toggler-icon")
  )

  val Companion = BootstrapNavbarComponents

  def withActiveUrl(activeUrl: String) = new BootstrapNavbarComponents(activeUrl)

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[Frag] = Seq.empty,
      right: Seq[Frag] = Seq.empty
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
          ul(cls := "navbar-nav")(left),
          ul(cls := "navbar-nav ms-auto")(right)
        )
      )
    )

  override def link(url: String, content: Frag): Frag =
    li(cls := "nav-item")(
      a(href := url, Option.when(url == activeUrl)(cls := "active"), cls := "nav-link")(content)
    )

  override def dropdown(
      content: Frag,
      dropdownItems: Seq[Frag] = Seq.empty
  ): Frag =
    li(cls := "nav-item dropdown")(
      a(cls := "nav-link dropdown-toggle", data.bs.toggle := "dropdown", href := "#")(
        content,
        raw(" <span class='caret'></span>")
      ),
      ul(cls := "dropdown-menu")(dropdownItems)
    )

  override def dropdownLink(url: String, content: Frag): Frag =
    li(
      a(href := url, Option.when(url == activeUrl)(cls := "active"), cls := "dropdown-item")(
        content
      )
    )

}
