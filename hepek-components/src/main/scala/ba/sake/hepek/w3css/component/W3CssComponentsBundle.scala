package ba.sake.hepek.w3css.component

import ba.sake.hepek.html.component.ComponentsBundle
import ba.sake.hepek.w3css.component.classes.W3CssClassesBundle
import ba.sake.hepek.plain.component.PlainLinkComponents
import ba.sake.hepek.plain.component.PlainMarkdownComponents

trait W3CssComponentsBundle
    extends ComponentsBundle
    with PlainLinkComponents
    with PlainMarkdownComponents {
  override val Form    = W3CssFormComponents()
  override val Grid    = W3CssGridComponents()
  override val Image   = W3CssImageComponents()
  override val Navbar  = W3CssNavbarComponents
  override val Panel   = W3CssPanelComponents()
  override val Classes = W3CssClassesBundle
}
