package ba.sake.hepek.bulma.grid

import ba.sake.hepek.bulma.BulmaModifier
import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object BulmaGridComponents extends BulmaGridComponents

trait BulmaGridComponents extends GridComponents {
  import GridComponents._
  import ba.sake.hepek.bulma.component.enrichCssClasses

  def row(modifiers: BulmaModifier*)(content: Frag*) =
    div(cls := enrichCssClasses("columns", modifiers))(content)

  override def row(content: Frag*) =
    div(cls := "columns")(content)

  override def row(half1: Half1, half2: Half2) =
    row(half1.content ++ half2.content)

  override def row(third1: Third1, third2: Third2, third3: Third3) =
    row(third1.content ++ third2.content ++ third3.content)

  override def half1(content: Frag*) = {
    val c = div(cls := "column")(content)
    Half1(List(c))
  }

  override def half2(content: Frag*) = {
    val c = div(cls := "column")(content)
    Half2(List(c))
  }

  override def third1(content: Frag*) = {
    val c = div(cls := "column")(content)
    Third1(List(c))
  }

  override def third2(content: Frag*) = {
    val c = div(cls := "column")(content)
    Third2(List(c))
  }

  override def third3(content: Frag*) = {
    val c = div(cls := "column")(content)
    Third3(List(c))
  }

}
