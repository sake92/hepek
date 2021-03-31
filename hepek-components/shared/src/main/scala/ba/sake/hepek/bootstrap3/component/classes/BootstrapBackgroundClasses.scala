package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses
import scalatags.text.Builder

trait BootstrapBackgroundClasses extends BackgroundClasses {
  override def bgPrimary: AttrPair[Builder,String] = cls := "bg-primary"
  override def bgSuccess: AttrPair[Builder,String] = cls := "bg-success"
  override def bgInfo: AttrPair[Builder,String]    = cls := "bg-info"
  override def bgWarning: AttrPair[Builder,String] = cls := "bg-warning"
  override def bgDanger: AttrPair[Builder,String]  = cls := "bg-danger"
}
