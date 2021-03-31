package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TextClasses
import scalatags.text.Builder

trait PlainTextClasses extends TextClasses {
  override def txtPrimary: AttrPair[Builder,String] = cls := "txt-primary"
  override def txtSuccess: AttrPair[Builder,String] = cls := "txt-success"
  override def txtInfo: AttrPair[Builder,String]    = cls := "txt-info"
  override def txtWarning: AttrPair[Builder,String] = cls := "txt-warning"
  override def txtDanger: AttrPair[Builder,String]  = cls := "txt-danger"

  override def txtAlignLeft: AttrPair[Builder,String]    = cls := "txt-left"
  override def txtAlignCenter: AttrPair[Builder,String]  = cls := "txt-center"
  override def txtAlignRight: AttrPair[Builder,String]   = cls := "txt-right"
  override def txtAlignJustify: AttrPair[Builder,String] = cls := "txt-justify"

  override def txtLowercase: AttrPair[Builder,String]  = cls := "txt-lowercase"
  override def txtUppercase: AttrPair[Builder,String]  = cls := "txt-uppercase"
  override def txtCapitalize: AttrPair[Builder,String] = cls := "txt-capitalize"
}
