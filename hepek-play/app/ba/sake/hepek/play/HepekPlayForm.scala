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

  private val DefaultLabel                   = ""
  private val DefaultHelp                    = ""
  private val DefaultTransform: Frag => Frag = identity

  def form(attrPairs: AttrPair*)(
      action: Call,
      method: String = "POST"
  )(content: Frag*): Frag = {
    val allAttrPairs = Seq(all.action := action.url, all.method := method) ++ attrPairs
    fc.form(allAttrPairs: _*)(content)
  }

  def inputText(attrs: AttrPair*)(
      field: Field,
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      validationState: Option[fc.ValidationState] = None,
      messages: Seq[String] = Seq.empty,
      transform: Frag => Frag = DefaultTransform
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputText(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTextArea(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputPassword(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputEmail(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputUrl(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTel(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputFile(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputColor(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputNumber(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputRange(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputTime(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputWeek(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputMonth(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputDate(attrs: _*)(
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
  )(implicit playMsgs: Messages): Frag = {
    val params = getNormalParams(field, label, help, validationState, messages, transform, attrs)
    fc.inputDateTimeLocal(attrs: _*)(
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
  ): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputCheckbox(attrs: _*)(field.name, labell, help)
  }

  def inputCheckboxes(
      field: Field,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      label: String = DefaultLabel,
      help: String = DefaultHelp,
      isInline: Boolean = true
  ): Frag = {
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
  ): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputRadio(field.name, valueAndLabelAndAttrs, labell, help, isInline, checkedValue)
  }

  def inputSelect(attrs: AttrPair*)(
      field: Field,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      label: String = DefaultLabel,
      help: String = DefaultHelp
  ): Frag = {
    val labell = getIfNotBlank(label) getOrElse field.label
    fc.inputSelect(attrs: _*)(field.name, valueAndLabelAndAttrs, labell, help)
  }

  def inputSelectGrouped(attrs: AttrPair*)(
      field: Field,
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      label: String = DefaultLabel,
      help: String = DefaultHelp
  ): Frag = {
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
      transform: Frag => Frag
  )

  private def getNormalParams(
      field: Field,
      label: String,
      help: String,
      validationState: Option[fc.ValidationState],
      messages: Seq[String],
      transform: Frag => Frag,
      attrs: Seq[AttrPair]
  )(implicit playMsgs: Messages): HepekInputNormalParams = {
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
      transform
    )
  }

  private def stateAndMessages(
      field: Field,
      validationState: Option[fc.ValidationState],
      messages: Seq[String]
  )(implicit playMsgs: Messages): (Option[fc.ValidationState], Seq[String]) = {
    val maybeValidationState = validationState orElse
      (if (field.hasErrors) Some(fc.ValidationState.Error) else None)
    val inputMessages = field.errors.map(_.format) ++ messages
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
