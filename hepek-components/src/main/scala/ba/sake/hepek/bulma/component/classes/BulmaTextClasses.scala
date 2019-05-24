package ba.sake.hepek.bulma.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.TextClasses

object BulmaTextClasses extends BulmaTextClasses

trait BulmaTextClasses extends TextClasses {

  def txtPrimary = cls := "has-text-primary"
  def txtSuccess = cls := "has-text-success"
  def txtInfo    = cls := "has-text-info"
  def txtWarning = cls := "has-text-warning"
  def txtDanger  = cls := "has-text-danger"

  def txtAlignLeft    = cls := "has-text-left"
  def txtAlignCenter  = cls := "has-text-centered"
  def txtAlignRight   = cls := "has-text-right"
  def txtAlignJustify = cls := "has-text-justified"

  def txtLowercase  = cls := "is-lowercase"
  def txtUppercase  = cls := "is-uppercase"
  def txtCapitalize = cls := "is-capitalized"

}
