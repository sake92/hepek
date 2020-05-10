package ba.sake.hepek.plain.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.ClassesBundle

object PlainClassesBundle extends PlainClassesBundle

trait PlainClassesBundle
    extends ClassesBundle
    with PlainBackgroundClasses
    with PlainTextClasses
    with PlainButtonClasses
    with PlainTableClasses {
  override def clsContainer      = cls := "container"
  override def clsContainerFluid = cls := "container"

  override def clsNoPrint = cls := "hidden-print"
}
