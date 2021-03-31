package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ClassesBundle
import scalatags.text.Builder

object BulmaClassesBundle extends BulmaClassesBundle

trait BulmaClassesBundle
    extends ClassesBundle
    with BulmaBackgroundClasses
    with BulmaTextClasses
    with BulmaButtonClasses
    with BulmaTableClasses {
  override def clsContainer: AttrPair[Builder,String]      = cls := "container"
  override def clsContainerFluid: AttrPair[Builder,String] = cls := "container"

  override def clsNoPrint: AttrPair[Builder,String] = cls := "is-hidden-print"
}
