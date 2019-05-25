package ba.sake.hepek.bulma.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.TextClasses

object BulmaTextClasses extends BulmaTextClasses

trait BulmaTextClasses extends TextClasses {

  override def txtPrimary = cls := "has-text-primary"
  override def txtSuccess = cls := "has-text-success"
  override def txtInfo    = cls := "has-text-info"
  override def txtWarning = cls := "has-text-warning"
  override def txtDanger  = cls := "has-text-danger"

  override def txtAlignLeft    = cls := "has-text-left"
  override def txtAlignCenter  = cls := "has-text-centered"
  override def txtAlignRight   = cls := "has-text-right"
  override def txtAlignJustify = cls := "has-text-justified"

  override def txtLowercase  = cls := "is-lowercase"
  override def txtUppercase  = cls := "is-uppercase"
  override def txtCapitalize = cls := "is-capitalized"

}
