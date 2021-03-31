package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ClassesBundle
import org.scalajs.dom.Element

object BootstrapClassesBundle extends BootstrapClassesBundle

trait BootstrapClassesBundle
    extends ClassesBundle
    with BootstrapBackgroundClasses
    with BootstrapTextClasses
    with BootstrapButtonClasses
    with BootstrapTableClasses {
  override def clsContainer: AttrPair[Element,String]      = cls := "container"
  override def clsContainerFluid: AttrPair[Element,String] = cls := "container-fluid"

  override def clsNoPrint: AttrPair[Element,String] = cls := "hidden-print"
}
