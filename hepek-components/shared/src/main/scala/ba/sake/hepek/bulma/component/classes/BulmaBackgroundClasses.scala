package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses
import scalatags.text.Builder

trait BulmaBackgroundClasses extends BackgroundClasses {
  override def bgPrimary: AttrPair[Builder,String] = cls := "has-background-primary"
  override def bgSuccess: AttrPair[Builder,String] = cls := "has-background-success"
  override def bgInfo: AttrPair[Builder,String]    = cls := "has-background-info"
  override def bgWarning: AttrPair[Builder,String] = cls := "has-background-warning"
  override def bgDanger: AttrPair[Builder,String]  = cls := "has-background-danger"
}
