package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.html.component.classes.ClassesBundle
import ba.sake.hepek.scalatags.all._

object BulmaClassesBundle extends BulmaClassesBundle

trait BulmaClassesBundle
    extends ClassesBundle
    with BulmaBackgroundClasses
    with BulmaTextClasses
    with BulmaButtonClasses
    with BulmaTableClasses {
  override def clsContainer      = cls := "container"
  override def clsContainerFluid = cls := "container"

  override def clsNoPrint = cls := "is-hidden-print"
}
