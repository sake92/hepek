package ba.sake.hepek.bootstrap3.component

import ba.sake.hepek.html.component.NavbarComponents
import ba.sake.hepek.scalatags.all, all.{style => _, Style => _, _}
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle._

class BootstrapNavbarComponents(
    activeUrl: String = "",
    style: BootstrapNavbarComponents.Style = BootstrapNavbarComponents.Style.Default,
    width: BootstrapNavbarComponents.Width = BootstrapNavbarComponents.Width.Fluid,
    position: BootstrapNavbarComponents.Position = BootstrapNavbarComponents.Position.FixedTop,
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

  def withActiveUrl(activeUrl: String) = new BootstrapNavbarComponents(activeUrl)

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[Frag] = Seq.empty,
      right: Seq[Frag] = Seq.empty
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
}

object BootstrapNavbarComponents {
  sealed trait Position:
    def classes: String

  object Position:
    case object FixedTop extends Position:
      def classes = "navbar-fixed-top"
    case object FixedBottom extends Position:
      def classes = "navbar-fixed-bottom"

  sealed trait Width:
    def classes: String

  object Width:
    case object Fixed extends Width:
      def classes = "container"
    case object Fluid extends Width:
      def classes = "container-fluid"

  sealed trait Style:
    def classes: String

  object Style:
    case object Default extends Style:
      def classes = "navbar-default"
    case object Inverse extends Style:
      def classes = "navbar-inverse"

}
