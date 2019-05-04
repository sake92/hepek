package ba.sake.hepek.html.component

import scalatags.Text.all._

trait GridComponents {
  import GridComponents._

  def mkRow(content: Frag*): Frag
  def mkCol2(index: Int, content: List[Frag]): Frag // make a "half"
  def mkCol3(index: Int, content: List[Frag]): Frag // make a "third"

  // ROW
  def row(half1: Half, half2: Half): Frag =
    mkRow(mkCol2(0, half1.content), mkCol2(1, half2.content))

  def row(third1: Third, third2: Third, third3: Third): Frag =
    mkRow(mkCol3(0, third1.content), mkCol3(1, third2.content), mkCol3(2, third3.content))

  def half(content: Frag*): Half = Half(content.toList)

  def third(content: Frag*): Third = Third(content.toList)

  /** Same on all screens, by default */
  def screenRatios: ScreenRatios = ScreenRatios(
    Ratios.Default,
    Option(Ratios.Default),
    Option(Ratios.Default),
    Option(Ratios.Default)
  )
}

object GridComponents {

  case class Half(content: List[Frag])
  case class Third(content: List[Frag])

  case class Ratio(values: List[Int])

  object Ratio {
    def apply(values: Int*): Ratio = new Ratio(values.toList)
  }

  case class Ratios(
      half: Ratio = Ratios.DefaultHalf,
      third: Ratio = Ratios.DefaultThird
  ) {
    require(half.values.length == 2, "Halves ratios must contain exactly 2 values.")
    require(third.values.length == 3, "Thirds ratios must contain exactly 3 values.")
    def withHalf(r: Ratio)  = copy(half = r)
    def withThird(r: Ratio) = copy(third = r)
  }

  case class ScreenRatios(
      lg: Ratios,
      md: Option[Ratios] = None,
      sm: Option[Ratios] = None,
      xs: Option[Ratios] = None
  ) {
    def withLg(r: Ratios)         = copy(lg = r)
    def withMd(r: Ratios)         = copy(md = Some(r))
    def withMd(r: Option[Ratios]) = copy(md = r)
    def withSm(r: Ratios)         = copy(sm = Some(r))
    def withSm(r: Option[Ratios]) = copy(sm = r)
    def withXs(r: Ratios)         = copy(xs = Some(r))
    def withXs(r: Option[Ratios]) = copy(xs = r)
    def withAll(r: Ratios)        = withLg(r).withMd(r).withSm(r).withXs(r)
  }

  object Ratios {
    val DefaultHalf  = Ratio(1, 1)
    val DefaultThird = Ratio(1, 1, 1)
    val Default      = Ratios(DefaultHalf, DefaultThird)
  }
}
