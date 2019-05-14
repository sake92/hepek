package ba.sake.hepek.play

import play.api.data.Form
import play.api.data.Field
import play.api.i18n.Messages
import play.api.mvc.Call
import ba.sake.hepek.play
import ba.sake.hepek.html.component.FormComponents

import scalatags.Text.all, all._

trait HepekPlayForm {
  import FormComponents._

  type FormImpl <: FormComponents

  val fc: FormImpl

  def form(attrPairs: AttrPair*)(
      action: Call,
      method: String = "POST"
  )(content: Frag*): Frag = {
    val allAttrPairs = Seq(all.action := action.url, all.method := method) ++ attrPairs
    fc.form(allAttrPairs: _*)(content)
  }

  def inputText(attrs: AttrPair*)(
      f: Field,
      help: String = "",
      validationState: Option[fc.ValidationState] = None,
      validationMessage: Option[String] = None
  )(
      implicit messages: Messages
  ): Frag = {
    val (state, msgs) = stateAndMessages(f, validationState, validationMessage)
    fc.inputText(attrs: _*)(f.name, f.label, f.id, f.value.getOrElse(""), help, state, msgs)
  }

  def inputPassword(attrs: AttrPair*)(
      f: Field,
      help: String = "",
      validationState: Option[fc.ValidationState] = None,
      validationMessage: Option[String] = None
  )(
      implicit messages: Messages
  ): Frag = {
    val (state, msgs) = stateAndMessages(f, validationState, validationMessage)
    fc.inputPassword(attrs: _*)(f.name, f.label, f.id, f.value.getOrElse(""), help, state, msgs)
  }

  private def stateAndMessages(
      f: Field,
      validationState: Option[fc.ValidationState] = None,
      validationMessage: Option[String] = None
  )(implicit messages: Messages): (Option[fc.ValidationState], Seq[String]) = {
    val maybeValidationState = validationState orElse
      (if (f.hasErrors) Some(fc.ValidationState.Error) else None)
    val inputMessages = f.errors.map(_.format) ++ validationMessage.toSeq
    (maybeValidationState, inputMessages)
  }

}
