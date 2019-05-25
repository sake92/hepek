package ba.sake.hepek.bootstrap3.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.TextClasses

object BootstrapTextClasses extends BootstrapTextClasses

trait BootstrapTextClasses extends TextClasses {

  override def txtPrimary = cls := "text-primary"
  override def txtSuccess = cls := "text-success"
  override def txtInfo    = cls := "text-info"
  override def txtWarning = cls := "text-warning"
  override def txtDanger  = cls := "text-danger"

  override def txtAlignLeft    = cls := "text-left"
  override def txtAlignCenter  = cls := "text-center"
  override def txtAlignRight   = cls := "text-right"
  override def txtAlignJustify = cls := "text-justify"

  override def txtLowercase  = cls := "text-lowercase"
  override def txtUppercase  = cls := "text-uppercase"
  override def txtCapitalize = cls := "text-capitalize"

}
