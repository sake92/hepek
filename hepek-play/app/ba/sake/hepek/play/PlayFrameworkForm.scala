package ba.sake.hepek.play

import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.scalatags.all
import play.api.data.Field
import play.api.i18n.Messages
import play.api.mvc.Call
import play.api.mvc.Request
import play.filters.csrf.CSRF

import all.*

final class PlayFrameworkForm private (val fc: FormComponents) {

  private val HandledAttrs = Set("required", "min", "max", "minlength", "maxlength", "pattern")

  private val DefaultLabel                   = ""
  private val DefaultHelp                    = ""
  private val DefaultTransform: Frag => Frag = identity

  def form(attrPairs: AttrPair*)(
      action: Call,
      method: String = "POST"
  )(content: Frag*)(using Request[_]): Frag = {
    val allAttrPairs = Seq(all.action := action.url, all.method := method) ++ attrPairs
    val csrf         = CSRF.getToken.map(token => fc.inputHidden(value := token.value)(token.name))
    val contentWithCsrf = frag(content, csrf)
    fc.form(allAttrPairs: _*)(contentWithCsrf)
  }

  def inputText(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputText(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputTextArea(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTextArea(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputPassword(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputPassword(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputEmail(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputEmail(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputUrl(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputUrl(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputTel(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTel(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputFile(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputFile(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputColor(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputColor(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputNumber(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputNumber(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputRange(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputRange(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputTime(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTime(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputWeek(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputWeek(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputMonth(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputMonth(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputDate(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputDate(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputDateTimeLocal(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(using Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputDateTimeLocal(params.attrs: _*)(
      params.name,
      params.label,
      params.help,
      params.validationState,
      params.messages
    )
  }

  def inputCheckbox(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp
  )(using Messages): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputCheckbox(attrs: _*)(field.name, labell, help)
  }

  def inputCheckboxes(
      field: Field,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      isInline: Boolean = true
  )(using Messages): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputCheckboxes(field.name, valueAndLabelAndAttrs, labell, help, isInline)
  }

  def inputRadio(
      field: Field,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      isInline: Boolean = true,
      checkedValue: String = ""
  )(using Messages): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputRadio(field.name, valueAndLabelAndAttrs, labell, help, isInline, checkedValue)
  }

  def inputSelect(attrs: AttrPair*)(
      field: Field,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      label: String = DefaultLabel,
      help: String = DefaultHelp
  )(using Messages): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputSelect(attrs: _*)(field.name, valueAndLabelAndAttrs, labell, help)
  }

  def inputSelectGrouped(attrs: AttrPair*)(
      field: Field,
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      label: String = DefaultLabel,
      help: String = DefaultHelp
  )(using Messages): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputSelectGrouped(attrs: _*)(field.name, valueAndLabelAndAttrsGrouped, labell, help)
  }

  /* helpers */
  private[hepek] case class HepekInputNormalParams(
      name: String,
      label: String,
      help: String,
      validationState: Option[fc.ValidationState],
      messages: Seq[String],
      transform: Frag => Frag,
      attrs: Seq[AttrPair]
  )

  private def getNormalParams(
      field: Field,
      label: String,
      help: String,
      validationState: Option[fc.ValidationState],
      messages: Seq[String],
      transform: Frag => Frag,
      attrs: Seq[AttrPair]
  )(using Messages): HepekInputNormalParams = {
    val labell        = getIfNotBlank(label) getOrElse field.label
    val (state, msgs) = stateAndMessages(field, validationState, messages)
    val inputAttrsFiltered = constraintAttrPairs(field, attrs) ++
      attrs.filterNot(ap => HandledAttrs.contains(ap.a.name)) ++
      Seq(id := field.id) ++ field.value.map(value := _)
    HepekInputNormalParams(
      field.name,
      labell,
      help,
      state,
      msgs,
      transform,
      inputAttrsFiltered
    )
  }

  private def stateAndMessages(
      field: Field,
      validationState: Option[fc.ValidationState],
      messages: Seq[String]
  )(using Messages): (Option[fc.ValidationState], Seq[String]) = {
    val maybeValidationState = validationState orElse
      (if (field.hasErrors) Some(fc.ValidationState.Error) else None)
    val inputMessages = field.errors.map(_.format) ++ messages
    val prettyInputMessages = inputMessages.map { m =>
      val trimmed = m.trim // append ". ", prettier when msgs are displayed inline
      trimmed + (if (trimmed.endsWith(".")) " " else ". ")
    }
    (maybeValidationState, prettyInputMessages)
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

object PlayFrameworkForm:
  def apply(fc: FormComponents) = new PlayFrameworkForm(fc)
