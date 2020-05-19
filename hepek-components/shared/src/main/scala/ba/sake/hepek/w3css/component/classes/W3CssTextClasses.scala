package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.classes.TextClasses

trait W3CssTextClasses extends TextClasses {
  override def txtPrimary = cls := "w3-teal"
  override def txtSuccess = cls := "w3-green"
  override def txtInfo    = cls := "w3-blue"
  override def txtWarning = cls := "w3-yellow"
  override def txtDanger  = cls := "w3-red"

  override def txtAlignLeft    = cls := "w3-left-align"
  override def txtAlignCenter  = cls := "w3-center"
  override def txtAlignRight   = cls := "w3-right-align"
  override def txtAlignJustify = cls := "w3-wide"

  override def txtLowercase  = cls := "w3-lowercase"  // TODO implement yourself...
  override def txtUppercase  = cls := "w3-uppercase"  // TODO implement yourself...
  override def txtCapitalize = cls := "w3-capitalize" // TODO implement yourself...
}
