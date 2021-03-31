package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.ClassesBundle

object W3CssClassesBundle extends W3CssClassesBundle

trait W3CssClassesBundle
    extends ClassesBundle
    with W3CssBackgroundClasses
    with W3CssTextClasses
    with W3CssButtonClasses
    with W3CssTableClasses {
  override def clsContainer      = cls := "w3-container"
  override def clsContainerFluid = cls := "w3-container"

  override def clsNoPrint = cls := "hidden-print"
}
