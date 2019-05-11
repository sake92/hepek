package ba.sake.hepek.html.component

import scalatags.Text.all
import all.{form => _, _}

object FormComponents extends FormComponents {
  trait Type { def classes: String = "" }
}

trait FormComponents {
  import FormComponents._

  private val ButtonLikeTypes = Set("button", "submit", "reset")
  private val HandledAttrs    = Set("type", "name", "id", "value") // handled explicitly

  private val DefaultLabel = ""
  private val DefaultId    = ""
  private val DefaultValue = ""

  def formType: Type = new Type {}

  def form(_formAttrs: AttrPair*)(content: Frag*): Frag = {
    val formAttrs = _formAttrs :+ (cls := formType.classes)
    all.form(formAttrs)(content)
  }

  /** general input "constructor", should override in impl */
  def inputWithType(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    if (isButtonLike(inputType)) {
      // no label for buttons: https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label#Buttons
      input(commonAttrs)
    } else {
      inputLabel match {
        case None => input(commonAttrs)
        case Some(inputLabel) =>
          label(inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
      }
    }
  }

  /* inputs */
  def inputText(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("text", _name, _label, _id, _value, _inputAttrs)

  def inputPassword(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("password", _name, _label, _id, _value, _inputAttrs)

  def inputEmail(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("email", _name, _label, _id, _value, _inputAttrs)

  def inputUrl(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("url", _name, _label, _id, _value, _inputAttrs)

  def inputTel(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("tel", _name, _label, _id, _value, _inputAttrs)

  def inputCheckbox(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("checkbox", _name, _label, _id, _value, _inputAttrs)

  def inputFile(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("file", _name, _label, _id, _value, _inputAttrs)

  def inputColor(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("color", _name, _label, _id, _value, _inputAttrs)

  def inputNumber(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("number", _name, _label, _id, _value, _inputAttrs)

  def inputRange(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("range", _name, _label, _id, _value, _inputAttrs)

  def inputTime(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("time", _name, _label, _id, _value, _inputAttrs)

  def inputWeek(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("week", _name, _label, _id, _value, _inputAttrs)

  def inputMonth(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("month", _name, _label, _id, _value, _inputAttrs)

  def inputDate(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("date", _name, _label, _id, _value, _inputAttrs)

  def inputDateTimeLocal(_inputAttrs: AttrPair*)(
      _name: String,
      _label: String = DefaultLabel,
      _id: String = DefaultId,
      _value: String = DefaultValue
  ): Frag =
    inputWithTypeCleaned("datetime-local", _name, _label, _id, _value, _inputAttrs)

  /* clickables, these don't have name field !? */
  def inputSubmit(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("submit", "", _label, _id, "", _inputAttrs)

  def inputButton(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("button", "", _label, _id, "", _inputAttrs)

  def inputReset(_inputAttrs: AttrPair*)(_label: String, _id: String = DefaultId): Frag =
    inputWithTypeCleaned("reset", "", _label, _id, "", _inputAttrs)

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
      _inputAttrs: Seq[AttrPair]
  ): Frag = {
    val inputName  = _name
    val inputLabel = getIfNotBlank(_label)
    val inputValue =
      if (isButtonLike(_type)) inputLabel
      else getIfNotBlank(_value) orElse getAttrValue(_inputAttrs, "value")
    val inputId = getIfNotBlank(_id) orElse getAttrValue(_inputAttrs, "id")

    // ignore handled attrs
    val inputAttrsFiltered = _inputAttrs.filterNot(ap => HandledAttrs.contains(ap.a.name))
    inputWithType(
      _type,
      inputName,
      inputLabel,
      inputId,
      inputValue,
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
