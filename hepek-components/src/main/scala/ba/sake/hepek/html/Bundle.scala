package ba.sake.hepek.html

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.html.component.classes.ClassesBundle

trait Bundle extends ComponentsBundle with HepekAliases {
  type ClassesBundle <: ba.sake.hepek.html.component.classes.ClassesBundle
  val classes: ClassesBundle

  type HtmlPage <: ba.sake.hepek.html.HtmlPage
}
