package ba.sake.hepek.bulma

import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle.*
import ba.sake.hepek.html.*
import ba.sake.hepek.scalatags.all.*

trait BulmaPage extends HtmlPage with BulmaDependencies {

  override def bodyContent: Frag =
    tag("section")(cls := "section")(
      div(clsContainer)(
        pageContent
      )
    )
}
