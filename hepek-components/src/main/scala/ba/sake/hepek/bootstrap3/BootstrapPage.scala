package ba.sake.hepek.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.html._

trait BootstrapPage extends HtmlPage with BootstrapDependencies {

  def bootstrapContainer: String = "container-fluid"

  override def bodyContent: Frag =
    div(cls := bootstrapContainer)(
      )

}
