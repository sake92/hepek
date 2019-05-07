package ba.sake.hepek.bulma.grid

import ba.sake.hepek.bulma.BulmaModifier
import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object BulmaGridComponents extends BulmaGridComponents

trait BulmaGridComponents extends GridComponents {
  import GridComponents._
  import ba.sake.hepek.bulma.component.enrichCssClasses

  private[hepek] override def mkRow(content: Frag*): Frag =
    div(cls := "columns")(content)

  // TODO add responsiveness
  private[hepek] override def mkCol2(index: Int, content: List[Frag]): Frag =
    div(cls := "column")(content)

  private[hepek] override def mkCol3(index: Int, content: List[Frag]): Frag =
    div(cls := "column")(content)

}
