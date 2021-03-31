package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ButtonClasses
import org.scalajs.dom.Element

trait BulmaButtonClasses extends ButtonClasses {
  override def btnClass: AttrPair[Element,String]   = cls := "button"
  override def btnPrimary: AttrPair[Element,String] = cls := "is-primary"
  override def btnSuccess: AttrPair[Element,String] = cls := "is-success"
  override def btnInfo: AttrPair[Element,String]    = cls := "is-info"
  override def btnWarning: AttrPair[Element,String] = cls := "is-warning"
  override def btnDanger: AttrPair[Element,String]  = cls := "is-danger"
  override def btnLink: AttrPair[Element,String]    = cls := "is-link"
  override def btnActive: AttrPair[Element,String]  = cls := "is-active"

  override def btnSizeLg: AttrPair[Element,String] = cls := "is-large"
  override def btnSizeMd: AttrPair[Element,String] = cls := "is-medium"
  override def btnSizeSm: AttrPair[Element,String] = cls := "is-small"
  override def btnSizeXs: AttrPair[Element,String] = cls := "is-small" // no extra-small in Bulma

  override def btnWidthFull: AttrPair[Element,String] = cls := "is-fullwidth"
}
