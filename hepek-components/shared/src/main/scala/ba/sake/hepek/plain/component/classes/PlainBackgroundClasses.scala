package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses
import org.scalajs.dom.Element

trait PlainBackgroundClasses extends BackgroundClasses {
  override def bgPrimary: AttrPair[Element,String] = cls := "bg-primary"
  override def bgSuccess: AttrPair[Element,String] = cls := "bg-success"
  override def bgInfo: AttrPair[Element,String]    = cls := "bg-info"
  override def bgWarning: AttrPair[Element,String] = cls := "bg-warning"
  override def bgDanger: AttrPair[Element,String]  = cls := "bg-danger"
}
