package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{BulmaModifier, EmptyAttribute}
import scalatags.Text.all._

object MessageComponents extends MessageComponents

trait MessageComponents {

  def messageHeader(content: Frag*) = div(cls := "message-header")(content)

  def messageBody(content: Frag*) = div(cls := "message-body")(content)

  def completeMessage(header: Frag*)(body: Frag*) =
    completeStyledMessage(EmptyAttribute)(header)(body)

  def completeStyledMessage(color: BulmaModifier)(header: Frag*)(body: Frag*) = {
    val cssClassValues = enrichCssClass("message", color)
    header.size match {
      case 0 => tag("article")(cls := cssClassValues, messageBody(body))
      case _ => tag("article")(cls := cssClassValues, messageHeader(header), messageBody(body))
    }
  }

}
