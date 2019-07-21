package ba.sake.hepek.html.component

import scalatags.Text.all._

trait GridComponents {
  import GridComponents._

  // utility for making a row with a single column
  private[hepek] def mkRowSingleCol(content: Seq[Frag]): Frag

  private[hepek] def mkRow(content: Frag*): Frag

  private[hepek] def mkCol2(index: Int, content: Col2): Frag
  private[hepek] def mkCol3(index: Int, content: Col3): Frag

  // ROW
  def row(content: Frag*): Frag =
    mkRowSingleCol(content)

  def row(c1: Col2, c2: Col2): Frag =
    mkRow(
      mkCol2(0, c1),
      mkCol2(1, c2)
    )

  def row(c1: Col3, c2: Col3, c3: Col3): Frag =
    mkRow(
      mkCol3(0, c1),
      mkCol3(1, c2),
      mkCol3(2, c3)
    )

  def half(content: Frag*): Col2 = Col2(content.toList)

  def third(content: Frag*): Col3 = Col3(content.toList)

  /** Same on all screens, by default */
  // lg is not optional, need to have at least one ratio...
  def screenRatios: ScreenRatios = ScreenRatios(
    Ratios.Default,
    Option(Ratios.Default),
    Option(Ratios.Default),
    Option(Ratios.Default)
  )
}

object GridComponents {

  case class Col2(content: List[Frag]) // 2 parts
  case class Col3(content: List[Frag]) // 3 parts

  case class Ratio(values: List[Int])

  object Ratio {
    def apply(values: Int*): Ratio = new Ratio(values.toList)
  }

  case class Ratios(
      single: Ratio = Ratios.DefaultSingle,
      half: Ratio = Ratios.DefaultHalf,
      third: Ratio = Ratios.DefaultThird
  ) {
    require(
      half.values.length == 2,
      s"Halves ratios must contain exactly 2 values. Actual: ${half.values}"
    )
    require(
      third.values.length == 3,
      s"Thirds ratios must contain exactly 3 values. Actual: ${third.values}"
    )
    
    def withSingle(r: Ratio)  = copy(single = r)
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
    val DefaultSingle = Ratio(0, 1, 0) // 0:1:0, no padding
    val DefaultHalf   = Ratio(1, 1)
    val DefaultThird  = Ratio(1, 1, 1)
    val Default       = Ratios(DefaultSingle, DefaultHalf, DefaultThird)
  }
}
