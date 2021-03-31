package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TextClasses
import org.scalajs.dom.Element

trait BulmaTextClasses extends TextClasses {
  override def txtPrimary: AttrPair[Element,String] = cls := "has-text-primary"
  override def txtSuccess: AttrPair[Element,String] = cls := "has-text-success"
  override def txtInfo: AttrPair[Element,String]    = cls := "has-text-info"
  override def txtWarning: AttrPair[Element,String] = cls := "has-text-warning"
  override def txtDanger: AttrPair[Element,String]  = cls := "has-text-danger"

  override def txtAlignLeft: AttrPair[Element,String]    = cls := "has-text-left"
  override def txtAlignCenter: AttrPair[Element,String]  = cls := "has-text-centered"
  override def txtAlignRight: AttrPair[Element,String]   = cls := "has-text-right"
  override def txtAlignJustify: AttrPair[Element,String] = cls := "has-text-justified"

  override def txtLowercase: AttrPair[Element,String]  = cls := "is-lowercase"
  override def txtUppercase: AttrPair[Element,String]  = cls := "is-uppercase"
  override def txtCapitalize: AttrPair[Element,String] = cls := "is-capitalized"
}
