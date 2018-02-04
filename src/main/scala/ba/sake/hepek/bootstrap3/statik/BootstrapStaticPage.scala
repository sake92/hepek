package ba.sake.hepek.bootstrap3

package statik

import scalatags.Text.all._
import ba.sake.hepek.html.structure.StaticPage

trait BootstrapStaticPage extends StaticPage with BootstrapDependencies {

  def beforePageContent: Frag = frag()

  def pageContent: Frag

  def afterPageContent: Frag = frag()

  def fluidContainer: Boolean = true

  override def bodyContent = {
    val containerClass = if (fluidContainer) "container-fluid" else "container"
    List(
      beforePageContent,
      div(cls := containerClass)(pageContent),
      afterPageContent
    )
  }

}
