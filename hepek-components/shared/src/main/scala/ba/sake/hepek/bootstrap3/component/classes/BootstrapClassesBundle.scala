package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.ClassesBundle
import ba.sake.hepek.scalatags.all.*

object BootstrapClassesBundle extends BootstrapClassesBundle

trait BootstrapClassesBundle
    extends ClassesBundle
    with BootstrapBackgroundClasses
    with BootstrapTextClasses
    with BootstrapButtonClasses
    with BootstrapTableClasses {
  override def clsContainer: AttrPair = cls := "container"

  override def clsContainerFluid: AttrPair = cls := "container-fluid"

  override def clsNoPrint: AttrPair = cls := "hidden-print"
}
