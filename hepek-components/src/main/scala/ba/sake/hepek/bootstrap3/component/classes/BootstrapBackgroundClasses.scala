package ba.sake.hepek.bootstrap3.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.BackgroundClasses

object BootstrapBackgroundClasses extends BootstrapBackgroundClasses

trait BootstrapBackgroundClasses extends BackgroundClasses {

  def bgPrimary = cls := "bg-primary"
  def bgSuccess = cls := "bg-success"
  def bgInfo    = cls := "bg-info"
  def bgWarning = cls := "bg-warning"
  def bgDanger  = cls := "bg-danger"
}
