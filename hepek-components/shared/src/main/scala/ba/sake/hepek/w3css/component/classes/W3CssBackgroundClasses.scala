package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses
import org.scalajs.dom.Element

trait W3CssBackgroundClasses extends BackgroundClasses {
  override def bgPrimary: AttrPair[Element,String] = cls := "w3-white"
  override def bgSuccess: AttrPair[Element,String] = cls := "w3-green"
  override def bgInfo: AttrPair[Element,String]    = cls := "w3-blue"
  override def bgWarning: AttrPair[Element,String] = cls := "w3-yellow"
  override def bgDanger: AttrPair[Element,String]  = cls := "w3-red"
}
