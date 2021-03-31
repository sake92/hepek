package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TextClasses
import org.scalajs.dom.Element

trait W3CssTextClasses extends TextClasses {
  override def txtPrimary: AttrPair[Element,String] = cls := "w3-teal"
  override def txtSuccess: AttrPair[Element,String] = cls := "w3-green"
  override def txtInfo: AttrPair[Element,String]    = cls := "w3-blue"
  override def txtWarning: AttrPair[Element,String] = cls := "w3-yellow"
  override def txtDanger: AttrPair[Element,String]  = cls := "w3-red"

  override def txtAlignLeft: AttrPair[Element,String]    = cls := "w3-left-align"
  override def txtAlignCenter: AttrPair[Element,String]  = cls := "w3-center"
  override def txtAlignRight: AttrPair[Element,String]   = cls := "w3-right-align"
  override def txtAlignJustify: AttrPair[Element,String] = cls := "w3-wide"

  override def txtLowercase: AttrPair[Element,String]  = cls := "w3-lowercase"  // TODO implement yourself...
  override def txtUppercase: AttrPair[Element,String]  = cls := "w3-uppercase"  // TODO implement yourself...
  override def txtCapitalize: AttrPair[Element,String] = cls := "w3-capitalize" // TODO implement yourself...
}
