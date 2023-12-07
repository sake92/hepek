package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.html.component.classes.BackgroundClasses
import ba.sake.hepek.scalatags.all.*

trait BulmaBackgroundClasses extends BackgroundClasses {
  override def bgPrimary = cls := "has-background-primary"
  override def bgSuccess = cls := "has-background-success"
  override def bgInfo    = cls := "has-background-info"
  override def bgWarning = cls := "has-background-warning"
  override def bgDanger  = cls := "has-background-danger"
}
