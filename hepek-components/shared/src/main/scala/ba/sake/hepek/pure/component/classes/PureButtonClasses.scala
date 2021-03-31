package ba.sake.hepek.pure.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ButtonClasses
import org.scalajs.dom.Element

trait PureButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair[Element,String]   = cls := "pure-button"
  override def btnPrimary: AttrPair[Element,String] = cls := "pure-button-primary"
  override def btnSuccess: AttrPair[Element,String] = cls := "button-success"
  override def btnInfo: AttrPair[Element,String]    = cls := "button-info"
  override def btnWarning: AttrPair[Element,String] = cls := "button-warning"
  override def btnDanger: AttrPair[Element,String]  = cls := "button-error"
  override def btnLink: AttrPair[Element,String]    = cls := "button-link"
  override def btnActive: AttrPair[Element,String]  = cls := "pure-button-active"

  override def btnSizeLg: AttrPair[Element,String] = cls := "button-xlarge"
  override def btnSizeMd: AttrPair[Element,String] = cls := "button-large"
  override def btnSizeSm: AttrPair[Element,String] = cls := "button-small"
  override def btnSizeXs: AttrPair[Element,String] = cls := "button-xsmall"

  override def btnWidthFull: AttrPair[Element,String] = cls := "button-block" // no support in Pure..
}
