package ba.sake.hepek.html.component.classes

import scalatags.Text.all._

trait ClassesBundle {
  self: BackgroundClasses with TextClasses with ButtonClasses with TableClasses =>

  def clsContainer: AttrPair
  def clsContainerFluid: AttrPair
}
