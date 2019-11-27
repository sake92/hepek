package ba.sake.hepek.w3css

import ba.sake.hepek.html.Bundle
import ba.sake.hepek.w3css.component.W3CssComponentsBundle
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle

trait W3CssBundle extends Bundle with W3CssComponentsBundle {
  override type ClassesBundle = W3CssClassesBundle
  override type HtmlPage      = W3CssPage

  override val classes = W3CssClassesBundle
}
