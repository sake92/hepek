package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.TextClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapTextClasses extends TextClasses {
  override def txtPrimary: AttrPair = cls := "text-primary"
  override def txtSuccess: AttrPair = cls := "text-success"
  override def txtInfo: AttrPair    = cls := "text-info"
  override def txtWarning: AttrPair = cls := "text-warning"
  override def txtDanger: AttrPair  = cls := "text-danger"

  override def txtAlignLeft: AttrPair    = cls := "text-left"
  override def txtAlignCenter: AttrPair  = cls := "text-center"
  override def txtAlignRight: AttrPair   = cls := "text-right"
  override def txtAlignJustify: AttrPair = cls := "text-justify"

  override def txtLowercase: AttrPair  = cls := "text-lowercase"
  override def txtUppercase: AttrPair  = cls := "text-uppercase"
  override def txtCapitalize: AttrPair = cls := "text-capitalize"
}
