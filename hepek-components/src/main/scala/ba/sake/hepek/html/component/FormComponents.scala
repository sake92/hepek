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

  // handled explicitly
  private val HandledAttrs         = Set("type", "name", "id", "value")
  private val HandledCheckboxAttrs = Set("type", "name", "value")
  private val HandledRadioAttrs    = Set("type", "name", "value", "checked")
  private val HandledSelectAttrs   = Set("type", "name", "id", "value")
  private val HandledOptionAttrs   = Set("type", "name", "value")

  private val DefaultLabel = ""
  private val DefaultHelp  = ""

  private val DefaultTransform: Frag => Frag = identity

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

  def formFieldset(legendTitle: String)(content: Frag*): Frag =
    fieldset(
      legend(legendTitle),
      content
    )

  /** normal input "constructor", should override in impl */
  def constructInputNormal(
      inputType: String,
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair],
      inputTransform: Frag => Frag
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputFrag =
      if (inputType == "textarea") textarea(commonAttrs)(inputValue)
      else input(commonAttrs)
    val inputFragTransformed = inputTransform(inputFrag)
    val inputContentFrag = inputLabel match {
      case None => inputFragTransformed
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          inputFragTransformed,
          inputLabel
        )
    }
    div(inputValidationState.map(cls := _.clazz))(
      inputFrag,
      inputMessages.map(span(_)),
      inputHelp.map(span(_))
    )
  }

  /** button input "constructor", should override in impl */
  def constructInputButton(
      inputType: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Frag, // <button> can have e.g. glyphs as content...
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label#Buttons
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/button#Validation
    // no label, name, validation
    // <button> is preferred to <input type="button">
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    if (inputType == "button") button(commonAttrs)(inputLabel)
    else input(commonAttrs)
  }

  /** checkbox input "constructor", should override in impl */
  def constructInputCheckbox(
      inputName: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox
    // no validation
    val commonAttrs = Seq(tpe := "checkbox", name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val inputFrag = inputLabel match {
      case None => input(commonAttrs)
      case Some(inputLabel) =>
        label(inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
    }
    div(
      inputFrag,
      inputHelp.map(span(_))
    )
  }

  def constructInputCheckboxes(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map {
      case (cbValue, cbLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "checkbox", name := inputName, value := cbValue) ++ inputAttrs
        label(input(commonAttrs), cbLabel)
    }

  def constructInputRadio(
      inputName: String,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag =
    valueAndLabelAndAttrs.map {
      case (radioValue, radioLabel, inputAttrs) =>
        val commonAttrs = Seq(tpe := "radio", name := inputName, value := radioValue) ++ inputAttrs
        label(input(commonAttrs), radioLabel)
    }

  def constructInputSelect(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionFrags = valueAndLabelAndAttrs.map {
      case (optionValue, optionLabel, optionAttrs) =>
        val commonAttrs = Seq(value := optionValue) ++ optionAttrs
        option(commonAttrs)(optionLabel)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName) ++ inputId.map(id := _)
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionFrags)
    )
  }

  // only possible attribute for <optgroup> is "disabled", so we dont bother...
  def constructInputSelectGrouped(
      inputName: String,
      inputId: Option[String],
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val optionGroupFrags = valueAndLabelAndAttrsGrouped.map {
      case (optGroupLabel, valueAndLabelAndAttrs) =>
        val optionFrags = valueAndLabelAndAttrs.map {
          case (optionValue, optionLabel, optionAttrs) =>
            val commonAttrs = Seq(value := optionValue) ++ optionAttrs
            option(commonAttrs)(optionLabel)
        }
        optgroup(attr("label") := optGroupLabel)(optionFrags)
    }
    val selectAttrs = inputAttrs ++ Seq(name := inputName) ++ inputId.map(id := _)
    div(
      inputLabel.map(l => label(inputId.map(`for` := _))),
      select(selectAttrs)(optionGroupFrags)
    )
  }

  /////////////////////////////////////////////////
  /* inputs */
  def inputText(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "text",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTextArea(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "textarea",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputPassword(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "password",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputEmail(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "email",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputUrl(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "url",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTel(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "tel",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputFile(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "file",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputColor(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "color",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputNumber(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "number",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputRange(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "range",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTime(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "time",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputWeek(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "week",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputMonth(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "month",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputDate(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "date",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputDateTimeLocal(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      "datetime-local",
      _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  /* buttons */
  def inputSubmit(_inputAttrs: AttrPair*)(_value: String): Frag =
    constructInputButtonCleaned("submit", _value, _value, _inputAttrs)

  def inputButton(_inputAttrs: AttrPair*)(_value: String, _label: Frag): Frag =
    constructInputButtonCleaned("button", _value, _label, _inputAttrs)

  def inputReset(_inputAttrs: AttrPair*)(_value: String): Frag =
    constructInputButtonCleaned("reset", _value, _value, _inputAttrs)

  /* hidden */
  def inputHidden(_inputAttrs: AttrPair*)(_name: String): Frag =
    input(tpe := "hidden", name := _name, _inputAttrs)

  /* checkboxes */
  def inputCheckbox(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag =
    constructInputCheckboxCleaned(
      _name,
      _label,
      _help,
      _inputAttrs
    )

  def inputCheckboxes(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _isInline: Boolean = true
  ): Frag =
    constructInputCheckboxesCleaned(
      _name,
      _valueAndLabelAndAttrs,
      _label,
      _help,
      _isInline
    )

  /* radios */
  def inputRadio(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _isInline: Boolean = true,
      _checkedValue: String = ""
  ): Frag =
    constructInputRadioCleaned(
      _name,
      _valueAndLabelAndAttrs,
      _label,
      _help,
      _isInline,
      _checkedValue
    )

  /* selects */
  def inputSelect(_inputAttrs: AttrPair*)(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag = constructInputSelectCleaned(
    _name,
    _valueAndLabelAndAttrs,
    _label,
    _help,
    _inputAttrs
  )

  def inputSelectGrouped(_inputAttrs: AttrPair*)(
      _name: String,
      _valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag = constructInputSelectGroupedCleaned(
    _name,
    _valueAndLabelAndAttrsGrouped,
    _label,
    _help,
    _inputAttrs
  )

  /* HELPERS */
  // delegates to constructInputNormal, after preparing necessary attributes
  private def constructInputNormalCleaned(
      _type: String,
      _name: String,
      _label: String,
      _help: String,
      _validationState: Option[ValidationState],
      _messages: Seq[String],
      _attrs: Seq[AttrPair],
      _transform: Frag => Frag
  ): Frag = {
    val inputId            = getAttrValue(_attrs, "id")
    val inputValue         = getAttrValue(_attrs, "value")
    val inputLabel         = getIfNotBlank(_label)
    val inputHelp          = getIfNotBlank(_help)
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputNormal(
      _type,
      _name,
      inputId,
      inputValue,
      inputLabel,
      inputHelp,
      _validationState,
      _messages,
      inputAttrsFiltered,
      _transform
    )
  }

  private def constructInputButtonCleaned(
      _type: String,
      _value: String,
      _label: Frag,
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputValue         = getIfNotBlank(_value)
    val inputId            = getAttrValue(_attrs, "id")
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputButton(
      _type,
      inputId,
      inputValue,
      _label,
      inputAttrsFiltered
    )
  }

  private def constructInputCheckboxCleaned(
      _name: String,
      _label: String,
      _help: String,
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputId            = getAttrValue(_attrs, "id")
    val inputValue         = getAttrValue(_attrs, "value")
    val inputLabel         = getIfNotBlank(_label)
    val inputHelp          = getIfNotBlank(_help)
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputCheckbox(
      _name,
      inputId,
      inputValue,
      inputLabel,
      inputHelp,
      inputAttrsFiltered
    )
  }

  private def constructInputCheckboxesCleaned(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String,
      _help: String,
      _isInline: Boolean
  ): Frag = {
    val valueAndLabelAndAttrsFiltered = _valueAndLabelAndAttrs.map {
      case (v, l, inputAttrs) =>
        (v, l, inputAttrs.filterNot(ap => HandledCheckboxAttrs.contains(ap.a.name)))
    }
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    constructInputCheckboxes(
      _name,
      valueAndLabelAndAttrsFiltered,
      inputLabel,
      inputHelp,
      _isInline
    )
  }

  private def constructInputRadioCleaned(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String,
      _help: String,
      _isInline: Boolean,
      _checkedValue: String
  ): Frag = {
    val valueAndLabelAndAttrsFiltered = _valueAndLabelAndAttrs.map {
      case (v, l, inputAttrs) =>
        val isChecked = getIfNotBlank(_checkedValue).filter(_ == v).map(_ => checked)
        (v, l, inputAttrs.filterNot(ap => HandledRadioAttrs.contains(ap.a.name)) ++ isChecked)
    }
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    constructInputRadio(
      _name,
      valueAndLabelAndAttrsFiltered,
      inputLabel,
      inputHelp,
      _isInline
    )
  }

  private def constructInputSelectCleaned(
      _name: String,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      _label: String,
      _help: String,
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputId    = getAttrValue(_attrs, "id")
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    val valueAndLabelAndAttrsFiltered = _valueAndLabelAndAttrs.map {
      case (v, l, inputAttrs) =>
        (v, l, inputAttrs.filterNot(ap => HandledOptionAttrs.contains(ap.a.name)))
    }
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledSelectAttrs.contains(ap.a.name))
    constructInputSelect(
      _name,
      inputId,
      valueAndLabelAndAttrsFiltered,
      inputLabel,
      inputHelp,
      inputAttrsFiltered
    )
  }

  private def constructInputSelectGroupedCleaned(
      _name: String,
      _valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      _label: String,
      _help: String,
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputId    = getAttrValue(_attrs, "id")
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    val valueAndLabelAndAttrsGroupedFiltered = _valueAndLabelAndAttrsGrouped.map {
      case (gl, _valueAndLabelAndAttrs) =>
        val valueAndLabelAndAttrsFiltered = _valueAndLabelAndAttrs.map {
          case (v, l, inputAttrs) =>
            (v, l, inputAttrs.filterNot(ap => HandledOptionAttrs.contains(ap.a.name)))
        }
        (gl, valueAndLabelAndAttrsFiltered)
    }
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledSelectAttrs.contains(ap.a.name))
    constructInputSelectGrouped(
      _name,
      inputId,
      valueAndLabelAndAttrsGroupedFiltered,
      inputLabel,
      inputHelp,
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
}
