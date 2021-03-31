package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ButtonClasses
import org.scalajs.dom.Element

trait PlainButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair[Element,String]   = cls := "btn"
  override def btnPrimary: AttrPair[Element,String] = cls := "btn-primary"
  override def btnSuccess: AttrPair[Element,String] = cls := "btn-success"
  override def btnInfo: AttrPair[Element,String]    = cls := "btn-info"
  override def btnWarning: AttrPair[Element,String] = cls := "btn-warning"
  override def btnDanger: AttrPair[Element,String]  = cls := "btn-danger"
  override def btnLink: AttrPair[Element,String]    = cls := "btn-link"
  override def btnActive: AttrPair[Element,String]  = cls := "btn-active"

  override def btnSizeLg: AttrPair[Element,String] = cls := "btn-lg"
  override def btnSizeMd: AttrPair[Element,String] = cls := "btn-md"
  override def btnSizeSm: AttrPair[Element,String] = cls := "btn-sm"
  override def btnSizeXs: AttrPair[Element,String] = cls := "btn-xs"

  override def btnWidthFull: AttrPair[Element,String] = cls := "btn-block"
}
