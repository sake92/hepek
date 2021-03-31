package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ButtonClasses
import scalatags.text.Builder

trait W3CssButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair[Builder,String]   = cls := "w3-btn"
  override def btnPrimary: AttrPair[Builder,String] = cls := "w3-teal"
  override def btnSuccess: AttrPair[Builder,String] = cls := "w3-green"
  override def btnInfo: AttrPair[Builder,String]    = cls := "w3-blue"
  override def btnWarning: AttrPair[Builder,String] = cls := "w3-yellow"
  override def btnDanger: AttrPair[Builder,String]  = cls := "w3-red"
  override def btnLink: AttrPair[Builder,String]    = cls := "w3-link"
  override def btnActive: AttrPair[Builder,String]  = cls := "active"

  override def btnSizeLg: AttrPair[Builder,String] = cls := "w3-xlarge"
  override def btnSizeMd: AttrPair[Builder,String] = cls := "w3-medium"
  override def btnSizeSm: AttrPair[Builder,String] = cls := "w3-small"
  override def btnSizeXs: AttrPair[Builder,String] = cls := "w3-tiny"

  override def btnWidthFull: AttrPair[Builder,String] = cls := "w3-block"
}
