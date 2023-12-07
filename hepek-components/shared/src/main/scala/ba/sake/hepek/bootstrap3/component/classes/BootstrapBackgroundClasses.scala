package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.BackgroundClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapBackgroundClasses extends BackgroundClasses {
  override def bgPrimary: AttrPair = cls := "bg-primary"
  override def bgSuccess: AttrPair = cls := "bg-success"
  override def bgInfo: AttrPair    = cls := "bg-info"
  override def bgWarning: AttrPair = cls := "bg-warning"
  override def bgDanger: AttrPair  = cls := "bg-danger"
}
