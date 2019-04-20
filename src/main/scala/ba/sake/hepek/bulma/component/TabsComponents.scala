package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma.{BulmaElement, BulmaModifier}
import scalatags.Text.all._

object TabsComponents extends TabsComponents

case class TabItem(label: String) extends BulmaElement {
  override def content = li(a(label))
}

trait TabsComponents {
  def tabsContainer(items: TabItem*) = ul(cls := "tabs")(items.map(_.content))

  def customTabsContainer(attributes: BulmaModifier*)(items: TabItem*) =
    ul(cls := enrichCssClasses("tabs", attributes))(items.map(_.content))
}
