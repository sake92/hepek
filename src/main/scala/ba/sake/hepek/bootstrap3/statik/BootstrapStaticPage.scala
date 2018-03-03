package ba.sake.hepek.bootstrap3

package statik

import scalatags.Text.all._
import ba.sake.hepek.html.structure.StaticPage

trait BootstrapStaticPage extends StaticPage with BootstrapDependencies {

  def pageContent: Frag

  def fluidContainer: Boolean = true

  override def bodyContent: List[Frag] = {
    val containerClass = if (fluidContainer) "container-fluid" else "container"
    List(
      div(cls := containerClass)(pageContent)
    )
  }

}
