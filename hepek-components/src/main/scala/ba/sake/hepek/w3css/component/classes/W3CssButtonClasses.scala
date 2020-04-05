package ba.sake.hepek.w3css.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.ButtonClasses

trait W3CssButtonClasses extends ButtonClasses {
  override def btnClass   = cls := "w3-btn"
  override def btnPrimary = cls := "w3-teal"
  override def btnSuccess = cls := "w3-green"
  override def btnInfo    = cls := "w3-blue"
  override def btnWarning = cls := "w3-yellow"
  override def btnDanger  = cls := "w3-red"
  override def btnLink    = cls := "w3-link"
  override def btnActive  = cls := "active"

  override def btnSizeLg = cls := "w3-xlarge"
  override def btnSizeMd = cls := "w3-medium"
  override def btnSizeSm = cls := "w3-small"
  override def btnSizeXs = cls := "w3-tiny"

  override def btnWidthFull = cls := "w3-block"
}
