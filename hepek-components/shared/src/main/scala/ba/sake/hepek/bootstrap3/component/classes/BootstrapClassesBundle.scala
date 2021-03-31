package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.ClassesBundle
import ba.sake.hepek.scalatags.all._

object BootstrapClassesBundle extends BootstrapClassesBundle

trait BootstrapClassesBundle
    extends ClassesBundle
    with BootstrapBackgroundClasses
    with BootstrapTextClasses
    with BootstrapButtonClasses
    with BootstrapTableClasses {
  override def clsContainer      = cls := "container"
  override def clsContainerFluid = cls := "container-fluid"

  override def clsNoPrint = cls := "hidden-print"
}
