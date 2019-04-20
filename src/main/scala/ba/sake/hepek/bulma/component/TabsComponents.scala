package ba.sake.hepek.bulma.component

import scalatags.Text.all._
import ba.sake.hepek.bulma.BulmaModifier

object TabsComponents extends TabsComponents

trait TabsComponents {
  def tabsContainer(content: Frag*) = ul(cls := "tabs")(content)

  def customTabsContainer(attributes: List[BulmaModifier])(content: Frag*) =
    ul(cls := s"tabs${cssClasses(attributes)}")(content)
}
