package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.ButtonClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair   = cls := "btn"
  override def btnPrimary: AttrPair = cls := "btn-primary"
  override def btnSuccess: AttrPair = cls := "btn-success"
  override def btnInfo: AttrPair    = cls := "btn-info"
  override def btnWarning: AttrPair = cls := "btn-warning"
  override def btnDanger: AttrPair  = cls := "btn-danger"
  override def btnLink: AttrPair    = cls := "btn-link"
  override def btnActive: AttrPair  = cls := "active"

  override def btnSizeLg: AttrPair = cls := "btn-lg"
  override def btnSizeMd: AttrPair = cls := "btn-md"
  override def btnSizeSm: AttrPair = cls := "btn-sm"
  override def btnSizeXs: AttrPair = cls := "btn-xs"

  override def btnWidthFull: AttrPair = cls := "btn-block"
}
