package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html.component.classes.BackgroundClasses

trait W3CssBackgroundClasses extends BackgroundClasses {
  override def bgPrimary = cls := "w3-white"
  override def bgSuccess = cls := "w3-green"
  override def bgInfo    = cls := "w3-blue"
  override def bgWarning = cls := "w3-yellow"
  override def bgDanger  = cls := "w3-red"
}
