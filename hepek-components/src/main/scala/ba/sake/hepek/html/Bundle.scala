package ba.sake.hepek.html

import ba.sake.hepek.html.component.ComponentsBundle

trait Bundle extends ComponentsBundle with HepekAliases {

  type HtmlPage <: ba.sake.hepek.html.HtmlPage
}
