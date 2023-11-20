package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.{form => _, _}

object FormComponents {
  trait Type { def classes: List[String] = List.empty }

  trait ValidationStateClasses {
    def success: AttrPair = cls := "success"
    def warning: AttrPair = cls := "warning"
    def error: AttrPair   = cls := "error"
  }
}

trait FormComponents {
  import FormComponents._

  // TODO maybe add idCounter for automatic labels

  // handled explicitly
  private val HandledAttrs = Set("type", "name", "id")

  private val DefaultName                      = ""
  private val DefaultLabel                     = ""
  private val DefaultHelp                      = ""
  private val DefaultTransform: (Frag => Frag) = identity

  protected def validationStateClasses: ValidationStateClasses

  def formType: Type

  def form(_formAttrs: AttrPair*)(content: Frag*): Frag = {
    val formAttrs = _formAttrs ++ formType.classes.map(cls := _)
    ba.sake.hepek.scalatags.all.form(formAttrs)(content)
  }

  def formFieldset(legendTitle: String)(content: Frag*): Frag =
    fieldset(
      legend(legendTitle),
      content
    )

  def inputText(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "text",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTextArea(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "textarea",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputPassword(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "password",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputEmail(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "email",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputUrl(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "url",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTel(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "tel",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputFile(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "file",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputColor(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "color",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputNumber(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "number",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputRange(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "range",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputTime(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "time",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputWeek(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "week",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputMonth(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "month",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputDate(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "date",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  def inputDateTimeLocal(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _validationState: Option[ValidationState] = None,
      _messages: Seq[String] = Seq.empty,
      _transform: Frag => Frag = DefaultTransform
  ): Frag =
    constructInputNormalCleaned(
      tpe := "datetime-local",
      name := _name,
      _label,
      _help,
      _validationState,
      _messages,
      _inputAttrs,
      _transform
    )

  /* buttons */
  def inputSubmit(_inputAttrs: AttrPair*)(_label: String): Frag =
    constructInputButtonCleaned("submit", _label, _inputAttrs)

  def inputReset(_inputAttrs: AttrPair*)(_label: String): Frag =
    constructInputButtonCleaned("reset", _label, _inputAttrs)

  def inputButton(_inputAttrs: AttrPair*)(_label: Frag): Frag =
    // <button> can have type "submit", let override win
    val btnTpe = getAttrValue(_inputAttrs, "type").getOrElse("button")
    constructInputButtonCleaned(btnTpe, _label, _inputAttrs)

  /* checkboxes */
  def inputCheckbox(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
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
      _name: String = DefaultName,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])] = Seq.empty,
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

  def inputCheckboxesSimple(
      _name: String = DefaultName,
      _valueAndLabels: Seq[(String, String)] = Seq.empty,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _isInline: Boolean = true
  ): Frag = {
    val _valueAndLabelAndAttrs = _valueAndLabels.map { case (v, l) => (v, l, Nil) }
    inputCheckboxes(_name, _valueAndLabelAndAttrs, _label, _help, _isInline)
  }

  /* radios */
  def inputRadio(
      _name: String = DefaultName,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])] = Seq.empty,
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

  def inputRadioSimple(
      _name: String = DefaultName,
      _valueAndLabels: Seq[(String, String)] = Seq.empty,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp,
      _isInline: Boolean = true,
      _checkedValue: String = ""
  ): Frag = {
    val _valueAndLabelAndAttrs = _valueAndLabels.map { case (v, l) => (v, l, Nil) }
    inputRadio(_name, _valueAndLabelAndAttrs, _label, _help, _isInline)
  }

  /* selects */
  def inputSelect(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])] = Seq.empty,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag = constructInputSelectCleaned(
    _name,
    _valueAndLabelAndAttrs,
    _label,
    _help,
    _inputAttrs
  )

  def inputSelectSimple(
      _name: String = DefaultName,
      _valueAndLabels: Seq[(String, String)] = Seq.empty,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag = {
    val _valueAndLabelAndAttrs = _valueAndLabels.map { case (v, l) => (v, l, Nil) }
    inputSelect()(_name, _valueAndLabelAndAttrs, _label, _help)
  }

  def inputSelectGrouped(_inputAttrs: AttrPair*)(
      _name: String = DefaultName,
      _valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])] = Seq.empty,
      _label: String = DefaultLabel,
      _help: String = DefaultHelp
  ): Frag = constructInputSelectGroupedCleaned(
    _name,
    _valueAndLabelAndAttrsGrouped,
    _label,
    _help,
    _inputAttrs
  )

  /* hidden */
  def inputHidden(_inputAttrs: AttrPair*)(_name: String): Frag =
    input(tpe := "hidden", name := _name, _inputAttrs)

  /* CONSTRUCTORS */
  protected def constructInputNormal(
      inputType: AttrPair,
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair],
      inputTransform: Frag => Frag
  ): Frag

  protected def constructInputButton(
      inputType: AttrPair,
      inputId: Option[String],
      inputLabel: Frag, // <button> can have e.g. glyphs as content...
      inputAttrs: Seq[AttrPair]
  ): Frag

  protected def constructInputCheckbox(
      inputName: AttrPair,
      inputId: Option[String],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag

  protected def constructInputCheckboxes(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag

  protected def constructInputRadio(
      inputName: AttrPair,
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      isInline: Boolean
  ): Frag

  protected def constructInputSelect(
      inputName: AttrPair,
      inputId: Option[String],
      valueAndLabelAndAttrs: Seq[(String, String, Seq[AttrPair])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag

  protected def constructInputSelectGrouped(
      inputName: AttrPair,
      inputId: Option[String],
      valueAndLabelAndAttrsGrouped: Seq[(String, Seq[(String, String, Seq[AttrPair])])],
      inputLabel: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag

  /* HELPERS */
  protected def getAttrValue(
      _inputAttrs: Seq[AttrPair],
      attrName: String
  ): Option[String] =
    _inputAttrs.find(_.a.name == attrName).map(_.toString)

  protected def getIfNotBlank(str: String): Option[String] = {
    val trimmed = str.trim
    if (trimmed.isEmpty) None else Some(trimmed)
  }

  private def constructInputNormalCleaned(
      _type: AttrPair,
      _name: AttrPair,
      _label: String,
      _help: String,
      _validationState: Option[ValidationState],
      _messages: Seq[String],
      _attrs: Seq[AttrPair],
      _transform: Frag => Frag
  ): Frag = {
    val inputId            = getAttrValue(_attrs, "id")
    val inputLabel         = getIfNotBlank(_label)
    val inputHelp          = getIfNotBlank(_help)
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputNormal(
      _type,
      _name,
      inputId,
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
      _label: Frag,
      _attrs: Seq[AttrPair]
  ): Frag = {
    val inputId            = getAttrValue(_attrs, "id")
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    inputAttrsFiltered.appended(value := _label.render.toString)
    constructInputButton(
      tpe := _type,
      inputId,
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
    val inputLabel         = getIfNotBlank(_label)
    val inputHelp          = getIfNotBlank(_help)
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputCheckbox(
      name := _name,
      inputId,
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
        (v, l, inputAttrs.filterNot(ap => HandledAttrs.contains(ap.a.name)))
    }
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    constructInputCheckboxes(
      name := _name,
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
        val isChecked: Option[AttrPair] =
          getIfNotBlank(_checkedValue).filter(_ == v).map(_ => attr("checked") := "checked")
        (v, l, inputAttrs.filterNot(ap => HandledAttrs.contains(ap.a.name)) ++ isChecked)
    }
    val inputLabel = getIfNotBlank(_label)
    val inputHelp  = getIfNotBlank(_help)
    constructInputRadio(
      name := _name,
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
        (v, l, inputAttrs.filterNot(ap => HandledAttrs.contains(ap.a.name)))
    }
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputSelect(
      name := _name,
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
            (v, l, inputAttrs.filterNot(ap => HandledAttrs.contains(ap.a.name)))
        }
        (gl, valueAndLabelAndAttrsFiltered)
    }
    val inputAttrsFiltered = _attrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    constructInputSelectGrouped(
      name := _name,
      inputId,
      valueAndLabelAndAttrsGroupedFiltered,
      inputLabel,
      inputHelp,
      inputAttrsFiltered
    )
  }

  sealed trait ValidationState { def clazz: AttrPair }

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
}
