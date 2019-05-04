package ba.sake.hepek.html.component

import scalatags.Text.all._

trait Modifiers {
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

object Bla extends App {
  import PureModifiers._
  println(div(btn)("aaaaaaaaaa"))
  println(div(btn, btnSuccess)("aaaaaaaaaa"))
}
