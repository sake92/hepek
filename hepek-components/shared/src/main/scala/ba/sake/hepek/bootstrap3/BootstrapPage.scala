package ba.sake.hepek.bootstrap3

import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.html._
import ba.sake.hepek.scalatags.all._

trait BootstrapPage extends HtmlPage with BootstrapDependencies {

  override def bodyContent: Frag =
    div(clsContainer)(
      pageContent
    )
}
