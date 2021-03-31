package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TextClasses
import scalatags.text.Builder

trait BootstrapTextClasses extends TextClasses {
  override def txtPrimary: AttrPair[Builder,String] = cls := "text-primary"
  override def txtSuccess: AttrPair[Builder,String] = cls := "text-success"
  override def txtInfo: AttrPair[Builder,String]    = cls := "text-info"
  override def txtWarning: AttrPair[Builder,String] = cls := "text-warning"
  override def txtDanger: AttrPair[Builder,String]  = cls := "text-danger"

  override def txtAlignLeft: AttrPair[Builder,String]    = cls := "text-left"
  override def txtAlignCenter: AttrPair[Builder,String]  = cls := "text-center"
  override def txtAlignRight: AttrPair[Builder,String]   = cls := "text-right"
  override def txtAlignJustify: AttrPair[Builder,String] = cls := "text-justify"

  override def txtLowercase: AttrPair[Builder,String]  = cls := "text-lowercase"
  override def txtUppercase: AttrPair[Builder,String]  = cls := "text-uppercase"
  override def txtCapitalize: AttrPair[Builder,String] = cls := "text-capitalize"
}
