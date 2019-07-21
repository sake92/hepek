package ba.sake.hepek.w3css.component

import scalatags.Text.all, all._
import ba.sake.hepek.html.component.PanelComponents

object W3CssPanelComponents extends W3CssPanelComponents {

  trait Type extends PanelComponents.Type

  object Type {
    case object Default extends Type { override def classes = List("w3-teal")   }
    case object Primary extends Type { override def classes = List("w3-teal")   }
    case object Success extends Type { override def classes = List("w3-green")  }
    case object Info    extends Type { override def classes = List("w3-blue")   }
    case object Warning extends Type { override def classes = List("w3-yellow") }
    case object Danger  extends Type { override def classes = List("w3-red")    }
  }
}

trait W3CssPanelComponents extends PanelComponents {
  import W3CssPanelComponents._

  override type PanelType = Type

  def panel(
      tpe: PanelType,
      body: Frag,
      header: Option[Frag] = None,
      footer: Option[Frag] = None
  ) =
    div(cls := "w3-panel w3-border")(
      all.header(cls := "w3-container", tpe.classes)(header),
      div(cls := "w3-container")(body),
      all.footer(cls := "w3-container", cls := "w3-teal")(footer)
    )
}
