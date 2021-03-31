package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ButtonClasses
import scalatags.text.Builder

trait BootstrapButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair[Builder,String]   = cls := "btn"
  override def btnPrimary: AttrPair[Builder,String] = cls := "btn-primary"
  override def btnSuccess: AttrPair[Builder,String] = cls := "btn-success"
  override def btnInfo: AttrPair[Builder,String]    = cls := "btn-info"
  override def btnWarning: AttrPair[Builder,String] = cls := "btn-warning"
  override def btnDanger: AttrPair[Builder,String]  = cls := "btn-danger"
  override def btnLink: AttrPair[Builder,String]    = cls := "btn-link"
  override def btnActive: AttrPair[Builder,String]  = cls := "active"

  override def btnSizeLg: AttrPair[Builder,String] = cls := "btn-lg"
  override def btnSizeMd: AttrPair[Builder,String] = cls := "btn-md"
  override def btnSizeSm: AttrPair[Builder,String] = cls := "btn-sm"
  override def btnSizeXs: AttrPair[Builder,String] = cls := "btn-xs"

  override def btnWidthFull: AttrPair[Builder,String] = cls := "btn-block"
}
