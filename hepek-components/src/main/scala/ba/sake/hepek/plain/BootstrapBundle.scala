package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component.PlainComponentsBundle
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

trait PlainBundle extends Bundle with PlainComponentsBundle {
  override type ClassesBundle = PlainClassesBundle
  override type HtmlPage      = PlainPage

  override val classes = PlainClassesBundle
}
