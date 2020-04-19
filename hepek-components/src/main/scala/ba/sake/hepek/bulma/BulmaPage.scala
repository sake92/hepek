package ba.sake.hepek.bulma

import scalatags.Text.all._
import ba.sake.hepek.html._
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle._

trait BulmaPage extends HtmlPage with BulmaDependencies {

  override def bodyContent: Frag =
    tag("section")(cls := "section")(
      div(clsContainer)(
        pageContent
      )
    )
}
