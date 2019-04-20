package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{BulmaElement, Large}
import scalatags.Text.all._

object ModalComponents extends ModalComponents

case class ModalCardBodyContent(elements: Frag*) extends BulmaElement {
  override def content = tag("section")(cls := "modal-card-body")(elements)
}

case class ModalCardFooterContent(elements: Frag*) extends BulmaElement {
  override def content = footer(cls := "modal-card-foot")(elements)
}

trait ModalComponents {

  def modal(content: Frag*) =
    div(cls := "modal")(
      div(cls := "modal-background"),
      div(cls := "modal-content")(content),
      button(cls := s"modal-close ${Large.classname}", aria.label := "close")
    )

  def modalCard(
      modalTitle: String,
      cardContent: ModalCardBodyContent,
      cardFooter: Option[ModalCardFooterContent]
  ) =
    div(cls := "modal")(
      div(cls := "modal-background"),
      div(cls := "modal-card")(
        header(cls := "modal-card-head")(
          p(cls := "modal-card-title")(modalTitle),
          button(cls := "delete", aria.label := "close")
        ),
        cardContent.content,
        optionalElementContent(cardFooter)
      ),
      button(cls := s"modal-close ${Large.classname}", aria.label := "close")
    )
}
