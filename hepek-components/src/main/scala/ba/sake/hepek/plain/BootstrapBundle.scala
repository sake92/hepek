package ba.sake.hepek.plain

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.plain.component.PlainComponentsBundle
import ba.sake.hepek.plain.component.classes.PlainClassesBundle

trait PlainBundle extends Bundle with PlainComponentsBundle {
  override type HtmlPage = PlainPage
}
