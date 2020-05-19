package ba.sake.hepek.bootstrap3

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek._
import ba.sake.hepek.html._
import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle._

trait BootstrapPage extends HtmlPage with BootstrapDependencies {
  def bootstrapContainer: AttrPair = clsContainerFluid

  override def bodyContent: Frag =
    div(bootstrapContainer)(
      pageContent
    )
}
