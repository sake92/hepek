package ba.sake.hepek.bootstrap5

import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.html._
import ba.sake.hepek.scalatags.all._

trait BootstrapPage extends HtmlPage with BootstrapDependencies {

  def bootstrapContainer: AttrPair = clsContainerFluid

  override def bodyContent: Frag =
    div(bootstrapContainer)(
      pageContent
    )

  override def stylesInline = List(
    """
    .affix {
        position: fixed;
        width: 10%;
    }
    """
  )
}
