package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ClassesBundle
import scalatags.text.Builder

object PlainClassesBundle extends PlainClassesBundle

trait PlainClassesBundle
    extends ClassesBundle
    with PlainBackgroundClasses
    with PlainTextClasses
    with PlainButtonClasses
    with PlainTableClasses {
  override def clsContainer: AttrPair[Builder,String]      = cls := "container"
  override def clsContainerFluid: AttrPair[Builder,String] = cls := "container"

  override def clsNoPrint: AttrPair[Builder,String] = cls := "hidden-print"
}
