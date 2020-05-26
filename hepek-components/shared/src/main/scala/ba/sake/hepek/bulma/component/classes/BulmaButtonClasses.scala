package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.classes.ButtonClasses

trait BulmaButtonClasses extends ButtonClasses {
  override def btnClass   = cls := "button"
  override def btnPrimary = cls := "is-primary"
  override def btnSuccess = cls := "is-success"
  override def btnInfo    = cls := "is-info"
  override def btnWarning = cls := "is-warning"
  override def btnDanger  = cls := "is-danger"
  override def btnLink    = cls := "is-link"
  override def btnActive  = cls := "is-active"

  override def btnSizeLg = cls := "is-large"
  override def btnSizeMd = cls := "is-medium"
  override def btnSizeSm = cls := "is-small"
  override def btnSizeXs = cls := "is-small" // no extra-small in Bulma

  override def btnWidthFull = cls := "is-fullwidth"
}
