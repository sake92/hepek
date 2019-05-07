package ba.sake.hepek.html.component

import scalatags.Text.all
import all.{form => _, _}

object FormComponents extends FormComponents

trait FormComponents {

  private val buttonLikeTypes = Set("button", "submit", "reset")

  def form(_formAttrs: AttrPair*)(content: Frag*): Frag =
    all.form(_formAttrs)(content)

  /* inputs */
  def inputText(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("text", _label, _inputAttrs: _*)

  def inputPassword(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("password", _label, _inputAttrs: _*)

  def inputEmail(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("email", _label, _inputAttrs: _*)

  def inputUrl(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("url", _label, _inputAttrs: _*)

  // handy for mobile phones, displays a keypad
  def inputTel(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("tel", _label, _inputAttrs: _*)

  def inputCheckbox(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("checkbox", _label, _inputAttrs: _*)

  /* numbers */
  def inputNumber(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("number", _label, _inputAttrs: _*)

  def inputRange(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("range", _label, _inputAttrs: _*)

  /* date, time */
  def inputTime(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("time", _label, _inputAttrs: _*)

  def inputWeek(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("week", _label, _inputAttrs: _*)

  def inputMonth(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("month", _label, _inputAttrs: _*)

  def inputDate(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("date", _label, _inputAttrs: _*)

  def inputDateTimeLocal(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("datetime-local", _label, _inputAttrs: _*)

  /* clickables */
  def inputSubmit(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("submit", _label, _inputAttrs: _*)

  def inputButton(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("button", _label, _inputAttrs: _*)

  def inputReset(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("reset", _label, _inputAttrs: _*)

  /* misc */
  def inputFile(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("file", _label, _inputAttrs: _*)

  def inputColor(_label: String, _inputAttrs: AttrPair*): Frag =
    inputWithType("color", _label, _inputAttrs: _*)

  def inputHidden(_inputAttrs: AttrPair*): Frag =
    input(tpe := "hidden", _inputAttrs)

  /* general input "constructor" */
  def inputWithType(
      _type: String,
      _label: String,
      _inputAttrs: AttrPair*
  ): Frag = {
    val inputId            = getAttrValue(_inputAttrs, "id")
    val inputAttrsFiltered = _inputAttrs.filterNot(_.a.name == "type") // ignore type
    if (_type == "checkbox") {
      label(inputId.map(`for` := _))(
        input(tpe := _type, inputAttrsFiltered),
        _label
      )
    } else if (isButtonLike(_type)) {
      val inputAttrsFiltered2 =
        inputAttrsFiltered.filterNot(_.a.name == "value") // ignore value
      input(tpe := _type, value := _label, inputAttrsFiltered2)
    } else {
      frag(
        label(inputId.map(`for` := _))(_label),
        input(tpe := _type, inputAttrsFiltered)
      )
    }
  }

  protected def getAttrValue(
      _inputAttrs: Seq[AttrPair],
      attrName: String
  ): Option[String] =
    _inputAttrs.find(_.a.name == attrName).map(_.v)

  protected def isButtonLike(_type: String) =
    buttonLikeTypes.contains(_type)
}
