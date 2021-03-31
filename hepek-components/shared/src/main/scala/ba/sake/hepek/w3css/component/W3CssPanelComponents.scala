package ba.sake.hepek.w3css.component

import ba.sake.hepek.scalatags.all, all._
import ba.sake.hepek.html.component.PanelComponents

object W3CssPanelComponents {
  trait Type extends PanelComponents.Type

  object Type {
    case object Default extends Type { override def classes: List[String] = List("w3-teal")   }
    case object Primary extends Type { override def classes: List[String] = List("w3-teal")   }
    case object Success extends Type { override def classes: List[String] = List("w3-green")  }
    case object Info    extends Type { override def classes: List[String] = List("w3-blue")   }
    case object Warning extends Type { override def classes: List[String] = List("w3-yellow") }
    case object Danger  extends Type { override def classes: List[String] = List("w3-red")    }
  }
}

case class W3CssPanelComponents() extends PanelComponents {
  import W3CssPanelComponents._

  val Companion = W3CssPanelComponents

  override type PanelType = Type

  def panel(
      panelType: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ): ba.sake.hepek.scalatags.TypedTag[org.scalajs.dom.html.Div] =
    div(cls := "w3-panel w3-border")(
      header.map(h => all.header(cls := "w3-container", panelType.classes.map(cls := _))(h)),
      div(cls := "w3-container")(body),
      footer.map(f => all.footer(cls := "w3-container", cls := "w3-teal")(f))
    )
}
