package ba.sake.hepek.bulma

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.bulma.component.BulmaComponentsBundle
import ba.sake.hepek.bulma.component.classes.BulmaClassesBundle

trait BulmaBundle extends Bundle with BulmaComponentsBundle {
  type ClassesBundle = BulmaClassesBundle
  val classes = BulmaClassesBundle

  override type HtmlPage = BulmaPage
}
