package ba.sake.hepek.pure.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.ButtonClasses

object PureButtonClasses extends PureButtonClasses

trait PureButtonClasses extends ButtonClasses {

  override def btnClass   = cls := "pure-button"
  override def btnPrimary = cls := "pure-button-primary"
  override def btnSuccess = cls := "button-success"
  override def btnInfo    = cls := "button-info"
  override def btnWarning = cls := "button-warning"
  override def btnDanger  = cls := "button-error"
  override def btnLink    = cls := "button-link"
  override def btnActive  = cls := "pure-button-active"

  override def btnSizeLg = cls := "button-xlarge"
  override def btnSizeMd = cls := "button-large"
  override def btnSizeSm = cls := "button-small"
  override def btnSizeXs = cls := "button-xsmall"

  override def btnWidthFull = cls := "button-block" // no support in Pure..
}
