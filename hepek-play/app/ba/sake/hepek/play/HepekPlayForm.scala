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

  private val HandledAttrs = Set("required", "min", "max", "minlength", "maxlength", "pattern")

  def form(attrPairs: AttrPair*)(
      action: Call,
      method: String = "POST"
  )(content: Frag*): Frag = {
    val allAttrPairs = Seq(all.action := action.url, all.method := method) ++ attrPairs
    fc.form(allAttrPairs: _*)(content)
  }

  def inputText(attrs: AttrPair*)(
      f: Field,
      label: String = "",
      help: String = "",
      validationState: Option[fc.ValidationState] = None,
      validationMessage: Option[String] = None
  )(
      implicit messages: Messages
  ): Frag = {
    val labell        = getIfNotBlank(label) getOrElse f.label
    val (state, msgs) = stateAndMessages(f, validationState, validationMessage)
    val inputAttrsFiltered = constraintAttrPairs(f, attrs) ++
      attrs.filterNot(ap => HandledAttrs.contains(ap.a.name)) ++
      Seq(id := f.id) ++ f.value.map(value := _)

    fc.inputText(inputAttrsFiltered: _*)(
      f.name,
      labell,
      help,
      state,
      msgs
    )
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
    val inputAttrsFiltered = constraintAttrPairs(f, attrs) ++
      attrs.filterNot(ap => HandledAttrs.contains(ap.a.name)) ++
      Seq(id := f.id) ++ f.value.map(value := _)
    fc.inputPassword(inputAttrsFiltered: _*)(f.name, f.label, help, state, msgs)
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

  private def getIfNotBlank(str: String): Option[String] = {
    val trimmed = str.trim
    if (trimmed.isEmpty) None else Some(trimmed)
  }

  // - stolen from https://github.com/adrianhurt/play-bootstrap/blob/master/core-play26/app/views/bs/package.scala#L118
  // - user can override Field's constraint with explicit _attrs
  private def constraintAttrPairs(field: Field, _attrs: Seq[AttrPair]): Seq[AttrPair] =
    field.constraints
      .collect {
        case ("constraint.required", _)       => "required"  -> "required"
        case ("constraint.min", params)       => "min"       -> params.head.toString
        case ("constraint.max", params)       => "max"       -> params.head.toString
        case ("constraint.minLength", params) => "minlength" -> params.head.toString
        case ("constraint.maxLength", params) => "maxlength" -> params.head.toString
        case ("constraint.pattern", params) =>
          val inputPattern = params.head match {
            case str: String => str
            case func: Function0[_] =>
              func.asInstanceOf[() => scala.util.matching.Regex]().toString
          }
          "pattern" -> inputPattern
      }
      .map { case (attrName, defaultValue) => attrPairOrElse(_attrs, attrName, defaultValue) }

  private def attrPairOrElse(
      _inputAttrs: Seq[AttrPair],
      attrName: String,
      defaultValue: String
  ): AttrPair =
    _inputAttrs.find(_.a.name == attrName) getOrElse (attr(attrName) := defaultValue)

}
