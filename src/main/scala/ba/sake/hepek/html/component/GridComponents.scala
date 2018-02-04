package ba.sake.hepek.html.component

import scalatags.Text.all._

trait GridComponents {
  import GridComponents._

  // ROW
  def row(content: Frag*): Frag
  def row(half1: Half1, half2: Half2): Frag
  def row(third1: Third1, third2: Third2, third3: Third3): Frag

  // HALF
  def half1(content: Frag*): Half1
  def half2(content: Frag*): Half2

  // THIRD
  def third1(content: Frag*): Third1
  def third2(content: Frag*): Third2
  def third3(content: Frag*): Third3

}

object GridComponents {

  case class Half1(content: List[Frag])
  case class Half2(content: List[Frag])

  case class Third1(content: List[Frag])
  case class Third2(content: List[Frag])
  case class Third3(content: List[Frag])

  case class Ratio(values: List[Int])
  case class Ratios(half: Ratio, third: Ratio)

  object Ratios {
    val DefaultHalf  = Ratio(List(1, 1))
    val DefaultThird = Ratio(List(1, 1, 1))
    val Default      = Ratios(DefaultHalf, DefaultThird)
  }

}
