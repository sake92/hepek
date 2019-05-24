package ba.sake.hepek.bulma.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses

object BulmaBackgroundClasses extends BulmaBackgroundClasses

trait BulmaBackgroundClasses extends BackgroundClasses {

  def bgPrimary = cls := "has-background-primary"
  def bgSuccess = cls := "has-background-success"
  def bgInfo    = cls := "has-background-info"
  def bgWarning = cls := "has-background-warning"
  def bgDanger  = cls := "has-background-danger"
}
