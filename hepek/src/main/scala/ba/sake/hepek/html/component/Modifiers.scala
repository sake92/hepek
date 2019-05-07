package ba.sake.hepek.html.component

import scalatags.Text.all._

trait Modifiers {
  // TODO extract common classes
  // https://www.w3schools.com/bootstrap/bootstrap_ref_css_buttons.asp
  // https://purecss.io/buttons/
  def btn: AttrPair
  def btnSuccess: AttrPair
}

object BsModifiers extends Modifiers {
  def btn        = cls := "btn"
  def btnSuccess = cls := "btn-success"
}

object PureModifiers extends Modifiers {
  def btn        = cls := "pure-button"
  def btnSuccess = cls := "pure-button-primary"
}
