package ba.sake.hepek.bootstrap5

import ba.sake.hepek.bootstrap5.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.html._
import ba.sake.hepek.scalatags.all._

trait BootstrapPage extends HtmlPage with BootstrapDependencies {

  override def bodyContent: Frag =
    div(clsContainer)(
      pageContent
    )

  override def stylesInline = List(
    """
    nav#tocScrollspy {
      display: none;
    }

    @media (min-width: 991px) {
      .affix {
          position: fixed;
          width: 10%;
      }

      nav#tocScrollspy {
        display: block;
      }
    }
    """
  )
}
