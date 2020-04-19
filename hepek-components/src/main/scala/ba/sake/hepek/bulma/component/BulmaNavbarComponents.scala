package ba.sake.hepek.bulma.component

import scalatags.Text.all.{style => _, Style => _, _}
import ba.sake.stone.Wither
import ba.sake.hepek.html.component.NavbarComponents

object BulmaNavbarComponent {
  sealed trait Position { def classes: String }

  object Position {
    case object FixedTop    extends Position { def classes = "is-fixed-top"    }
    case object FixedBottom extends Position { def classes = "is-fixed-bottom" }
  }

  sealed trait Width { def classes: String }

  object Width {
    case object Fixed extends Width { def classes = "container"       }
    case object Fluid extends Width { def classes = "container-fluid" }
  }

  case class Style(classes: String)
}

@Wither
case class BulmaNavbarComponent(
    style: Option[BulmaNavbarComponent.Style] = None,
    position: Option[BulmaNavbarComponent.Position] = Some(BulmaNavbarComponent.Position.FixedTop),
    collapseId: String = "main-navbar"
) extends NavbarComponents {
  import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle._

  val Companion = BulmaNavbarComponent

  private val bsBtn = tag("button")(tpe := "button", btnClass)

  def toggle =
    a(
      cls := "navbar-burger burger",
      data.target := collapseId,
      onclick := "document.querySelector('.navbar-menu').classList.toggle('is-active');"
      // ugly hack: https://github.com/jgthms/bulma/issues/856#issuecomment-502072770
    )(
      span(),
      span(),
      span()
    )

  override def full(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
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
        div(cls := "navbar-start")(
          left.map {
            case (item, liMods) =>
              div((cls := "navbar-item") +: liMods)(item)
          }
        ),
        div(cls := "navbar-end")(
          right.map {
            case (item, liMods) =>
              div((cls := "navbar-item") +: liMods)(item)
          }
        )
      )
    )

  override def fullNestedLink(
      title: Frag,
      links: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag =
    div(cls := "navbar-item has-dropdown is-hoverable")(
      a(cls := "navbar-link")(
        title
      ),
      div(cls := "navbar-dropdown")(
        links.map {
          case (item, liMods) =>
            div((cls := "navbar-item") +: liMods)(item)
        }
      )
    )
}
