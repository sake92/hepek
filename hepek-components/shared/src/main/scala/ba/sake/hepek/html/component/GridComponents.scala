package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all._
import ba.sake.kalem.Wither

trait GridComponents {
  import GridComponents._

  val Companion = GridComponents

  def screenRatios: ScreenRatios

  def row(content: Frag*): Frag =
    mkRowSingleCol(content)

  def row(col1: Col2, col2: Col2): Frag =
    mkRow(
      mkCol2(0, col1),
      mkCol2(1, col2)
    )

  def row(col1: Col3, col2: Col3, col3: Col3): Frag =
    mkRow(
      mkCol3(0, col1),
      mkCol3(1, col2),
      mkCol3(2, col3)
    )

  def half(content: Frag*): Col2 = Col2(content.toList)

  def third(content: Frag*): Col3 = Col3(content.toList)

  /* constructors */
  protected def mkRow(content: Frag*): Frag

  protected def mkRowSingleCol(content: Seq[Frag]): Frag
  protected def mkCol2(index: Int, content: Col2): Frag
  protected def mkCol3(index: Int, content: Col3): Frag
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

  final case class Col2(content: List[Frag])
  final case class Col3(content: List[Frag])

  final case class Ratio(values: List[Int])

  object Ratio {
    def apply(values: Int*): Ratio = new Ratio(values.toList)
  }

  @Wither
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

  @Wither
  final case class ScreenRatios(
      lg: Ratios,
      md: Option[Ratios] = None,
      sm: Option[Ratios] = None,
      xs: Option[Ratios] = None
  ) {
    def withAll(r: Ratios)               = withLg(r).withMd(r).withSm(r).withXs(r)
    def withLg(lg: Ratios): ScreenRatios = new ScreenRatios(lg = lg, md = md, sm = sm, xs = xs)

    def withMd(md: Option[Ratios]): ScreenRatios =
      new ScreenRatios(lg = lg, md = md, sm = sm, xs = xs)

    def withMd(md: Ratios): ScreenRatios =
      new ScreenRatios(lg = lg, sm = sm, xs = xs, md = Option(md))

    def withSm(sm: Option[Ratios]): ScreenRatios =
      new ScreenRatios(lg = lg, md = md, sm = sm, xs = xs)

    def withSm(sm: Ratios): ScreenRatios =
      new ScreenRatios(lg = lg, md = md, xs = xs, sm = Option(sm))

    def withXs(xs: Option[Ratios]): ScreenRatios =
      new ScreenRatios(lg = lg, md = md, sm = sm, xs = xs)

    def withXs(xs: Ratios): ScreenRatios =
      new ScreenRatios(lg = lg, md = md, sm = sm, xs = Option(xs))
  }

  object Ratios {
    val DefaultSingle = Ratio(0, 1, 0) // no padding
    val DefaultHalf   = Ratio(1, 1)
    val DefaultThird  = Ratio(1, 1, 1)
    val Default       = Ratios(DefaultSingle, DefaultHalf, DefaultThird)
  }
}
