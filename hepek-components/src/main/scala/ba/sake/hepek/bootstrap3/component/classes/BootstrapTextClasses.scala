package ba.sake.hepek.bootstrap3.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.TextClasses

object BootstrapTextClasses extends BootstrapTextClasses

trait BootstrapTextClasses extends TextClasses {

  def txtPrimary = cls := "text-primary"
  def txtSuccess = cls := "text-success"
  def txtInfo    = cls := "text-info"
  def txtWarning = cls := "text-warning"
  def txtDanger  = cls := "text-danger"

  def txtAlignLeft    = cls := "text-left"
  def txtAlignCenter  = cls := "text-center"
  def txtAlignRight   = cls := "text-right"
  def txtAlignJustify = cls := "text-justify"

  def txtLowercase  = cls := "text-lowercase"
  def txtUppercase  = cls := "text-uppercase"
  def txtCapitalize = cls := "text-capitalize"

}
