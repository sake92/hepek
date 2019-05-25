package ba.sake.hepek.html.component

import scalatags.Text.all
import all.{form => _, _}

object FormComponents extends FormComponents {

  trait Type { def classes: List[String] = List.empty }

  trait ValidationStateClasses {
    def success: String = "success"
    def warning: String = "warning"
    def error: String   = "error"
  }

}

trait FormComponents {
  import FormComponents._

  private val ButtonLikeTypes = Set("button", "submit", "reset")
  private val HandledAttrs    = Set("type", "name", "id", "value") // handled explicitly

  private val DefaultLabel = ""
  private val DefaultId    = ""
  private val DefaultValue = ""
  private val DefaultHelp  = ""

  /* Validation stuff */
  def validationStateClasses: ValidationStateClasses = new ValidationStateClasses {
    override def success: String = "success"
    override def warning: String = "warning"
    override def error: String   = "error"
  }

  sealed trait ValidationState { def clazz: String = "" }

  object ValidationState {
    case object Success extends ValidationState {
      override def clazz = validationStateClasses.success
    }
    case object Warning extends ValidationState {
      override def clazz = validationStateClasses.warning
    }
    case object Error extends ValidationState {
      override def clazz = validationStateClasses.error
    }
  }

  /* Form construction */
  def formType: Type = new Type {}

  def form(_formAttrs: AttrPair*)(content: Frag*): Frag = {
    val formAttrs = _formAttrs ++ formType.classes.map(cls := _)
    all.form(formAttrs)(content)
  }

  /** general input "constructor", should override in impl */
  def inputWithType(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputHelp: Option[String], // help text
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String], // error/warning messages
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    if (isButtonLike(inputType)) {
      // no label for buttons: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label#Buttons
      input(commonAttrs)
    } else {
      val inputField = inputLabel match {
        case None => input(commonAttrs)
        case Some(inputLabel) =>
          label(inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
      }
      div(inputValidationState.map(cls := _.clazz))(
        inputField,
        inputMessages.map(span(_)), // first show the errors.. :)
        inputHelp.map(span(_))
      )
    }
  }

  /* inputs */
  def inputText(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "text",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputPassword(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "password",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputEmail(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "email",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputUrl(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "url",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputTel(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "tel",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputCheckbox(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "checkbox",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputFile(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "file",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputColor(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "color",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputNumber(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "number",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputRange(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "range",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputTime(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "time",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputWeek(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "week",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputMonth(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "month",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputDate(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "date",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  def inputDateTimeLocal(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty
  ): Frag =
    inputWithTypeCleaned(
      "datetime-local",
      _name,
      _label,
      _id,
      _value,
      _help,
      _validationState,
      _messages,
      _inputAttrs
    )

  /* clickables */
  // no name, errors, help
  def inputSubmit(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("submit", "", _label, _id, "", "", None, Seq.empty, _inputAttrs)

  def inputButton(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("button", "", _label, _id, "", "", None, Seq.empty, _inputAttrs)

  def inputReset(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("reset", "", _label, _id, "", "", None, Seq.empty, _inputAttrs)

  /* misc */
  def inputHidden(_inputAttrs: AttrPair*)(_name: String): Frag =
    input(tpe := "hidden", name := _name, _inputAttrs)

  // delegates to inputWithType
  // after preparing necessary attributes
  private def inputWithTypeCleaned(
      _type: String,
      _name: String,
      _label: String,
      _id: String,
      _value: String,
      _help: String,
      _validationState: Option[ValidationState],
      _messages: Seq[String],
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputLabel = getIfNotBlank(_label)
    val inputValue =
      if (isButtonLike(_type)) inputLabel
      else getIfNotBlank(_value) orElse getAttrValue(_attrs, "value")
    val inputId   = getIfNotBlank(_id) orElse getAttrValue(_attrs, "id")
    val inputHelp = getIfNotBlank(_help)

    // ignore handled attrs
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    inputWithType(
      _type,
      _name,
      inputLabel,
      inputId,
      inputValue,
      inputHelp,
      _validationState,
      _messages,
      inputAttrsFiltered
    )
  }

  protected def getAttrValue(
      _inputAttrs: Seq[AttrPair],
      attrName: String
  ): Option[String] =
    _inputAttrs.find(_.a.name == attrName).map(_.v)

  protected def getIfNotBlank(str: String): Option[String] = {
    val trimmed = str.trim
    if (trimmed.isEmpty) None else Some(trimmed)
  }

  protected def isButtonLike(_type: String) =
    ButtonLikeTypes.contains(_type)
}
