package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.classes.TextClasses

trait PlainTextClasses extends TextClasses {
  override def txtPrimary = cls := "txt-primary"
  override def txtSuccess = cls := "txt-success"
  override def txtInfo    = cls := "txt-info"
  override def txtWarning = cls := "txt-warning"
  override def txtDanger  = cls := "txt-danger"

  override def txtAlignLeft    = cls := "txt-left"
  override def txtAlignCenter  = cls := "txt-center"
  override def txtAlignRight   = cls := "txt-right"
  override def txtAlignJustify = cls := "txt-justify"

  override def txtLowercase  = cls := "txt-lowercase"
  override def txtUppercase  = cls := "txt-uppercase"
  override def txtCapitalize = cls := "txt-capitalize"
}
