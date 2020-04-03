package ba.sake.hepek.html

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.html.component.classes.ClassesBundle

trait Bundle extends ComponentsBundle {
  type HtmlPage <: ba.sake.hepek.html.HtmlPage
}
