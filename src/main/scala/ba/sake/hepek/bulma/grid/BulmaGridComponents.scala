package ba.sake.hepek.bulma.grid

import ba.sake.hepek.bulma.BulmaModifier
import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object BulmaGridComponents extends BulmaGridComponents

trait BulmaGridComponents extends GridComponents {
  import GridComponents._
  import ba.sake.hepek.bulma.component.enrichCssClasses

  override def mkRow(content: Frag*): Frag =
    div(cls := "columns")(content)

  // TODO add responsiveness
  override def mkCol2(index: Int, content: List[Frag]): Frag =
    div(cls := "column")(content)

  override def mkCol3(index: Int, content: List[Frag]): Frag =
    div(cls := "column")(content)

  def row(modifiers: BulmaModifier*)(content: Frag*) =
    div(cls := enrichCssClasses("columns", modifiers))(content)
}
