package ba.sake.hepek.html.component

import scalatags.Text.all._

trait GridComponents {
  import GridComponents._

  /** Same on all screens, by default */
  def screenRatios: ScreenRatios = ScreenRatios(
    Ratios.Default,
    Option(Ratios.Default),
    Option(Ratios.Default),
    Option(Ratios.Default)
  )

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
  case class Ratios(half: Ratio, third: Ratio) {
    require(half.values.length == 2,
            "Halves ratios must contain exactly 2 values.")
    require(third.values.length == 3,
            "Thirds ratios must contain exactly 3 values.")
  }
  case class ScreenRatios(
      lg: Ratios,
      md: Option[Ratios] = None,
      sm: Option[Ratios] = None,
      xs: Option[Ratios] = None
  )

  object Ratios {
    val DefaultHalf  = Ratio(List(1, 1))
    val DefaultThird = Ratio(List(1, 1, 1))
    val Default      = Ratios(DefaultHalf, DefaultThird)
  }
}
