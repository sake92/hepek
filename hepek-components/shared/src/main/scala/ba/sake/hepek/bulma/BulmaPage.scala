package ba.sake.hepek.bulma

import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle._
import ba.sake.hepek.html._
import ba.sake.hepek.scalatags.all._

trait BulmaPage extends HtmlPage with BulmaDependencies {

  override def bodyContent: Frag =
    tag("section")(cls := "section")(
      div(clsContainer)(
        pageContent
      )
    )
}
