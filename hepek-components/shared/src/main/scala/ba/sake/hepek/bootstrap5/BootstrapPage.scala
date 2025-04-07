package ba.sake.hepek.bootstrap5

import ba.sake.hepek.html.*

trait BootstrapPage extends HtmlPage with BootstrapDependencies {

  override def stylesInline = List(
    """
    nav#tocScrollspy {
      display: none;
    }

    .affix {
      width: 100%;
    }

    @media (min-width: 991px) {
      .affix {
        position: fixed;
        width: 15%;
        height: 80vh;
        overflow: auto;
      }

      nav#tocScrollspy {
        display: block;
      }
    }
    """
  )
}
