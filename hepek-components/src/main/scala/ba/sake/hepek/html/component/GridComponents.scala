package ba.sake.hepek.html.component

import scalatags.Text.all._

trait GridComponents {
  import GridComponents._

  val screenRatios: ScreenRatios

  // utility for making a row with a single column
  protected def mkRowSingleCol(content: Seq[Frag]): Frag

  protected def mkRow(content: Frag*): Frag

  protected def mkCol2(index: Int, content: Col2): Frag
  protected def mkCol3(index: Int, content: Col3): Frag

  // API
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
}

object GridComponents {

  // Same on all screens
  // lg is not optional, need to have at least one ratio...
  val DefaultScreenRatios = ScreenRatios(
    Ratios.Default,
    Option(Ratios.Default),
    Option(Ratios.Default),
    Option(Ratios.Default)
  )
  final case class Col2(content: List[Frag]) // 2 parts
  final case class Col3(content: List[Frag]) // 3 parts

  final case class Ratio(values: List[Int])

  object Ratio {
    def apply(values: Int*): Ratio = new Ratio(values.toList)
  }

  final case class Ratios(
      single: Ratio = Ratios.DefaultSingle,
      half: Ratio = Ratios.DefaultHalf,
      third: Ratio = Ratios.DefaultThird
  ) {
    require(
      single.values.length == 3,
      s"Single ratios must contain exactly 3 values (padding-left, content, padding-right). Actual: ${half.values}"
    )
    require(
      half.values.length == 2,
      s"Halves ratios must contain exactly 2 values. Actual: ${half.values}"
    )
    require(
      third.values.length == 3,
      s"Thirds ratios must contain exactly 3 values. Actual: ${third.values}"
    )

    def withSingle(r: Ratio): Ratios                  = copy(single = r)
    def withSingle(r1: Int, r2: Int, r3: Int): Ratios = withSingle(Ratio(r1, r2, r3))

    def withHalf(r: Ratio): Ratios         = copy(half = r)
    def withHalf(r1: Int, r2: Int): Ratios = withHalf(Ratio(r1, r2))

    def withThird(r: Ratio): Ratios                  = copy(third = r)
    def withThird(r1: Int, r2: Int, r3: Int): Ratios = withThird(Ratio(r1, r2, r3))
  }

  final case class ScreenRatios(
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
