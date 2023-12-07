package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.*

trait GridComponents {
  import GridComponents.*

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

  final class Col2 private (val content: List[Frag])
  object Col2:
    def apply(content: List[Frag]): Col2 = new Col2(content)

  final class Col3 private (val content: List[Frag])
  object Col3:
    def apply(content: List[Frag]): Col3 = new Col3(content)

  final class Ratio private (val values: List[Int])
  object Ratio:
    def apply(values: Int*): Ratio = new Ratio(values.toList)

  final class Ratios private (
      val single: Ratio,
      val half: Ratio,
      val third: Ratio
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

    private def copy(
        single: Ratio = single,
        half: Ratio = half,
        third: Ratio = third
    ) = new Ratios(single, half, third)
  }

  object Ratios:
    val defaultSingle: Ratio = Ratio(0, 1, 0) // no padding
    val defaultHalf: Ratio   = Ratio(1, 1)
    val defaultThird: Ratio  = Ratio(1, 1, 1)
    val default: Ratios      = new Ratios(defaultSingle, defaultHalf, defaultThird)

  final class ScreenRatios private (
      val lg: Ratios,
      val md: Option[Ratios] = None,
      val sm: Option[Ratios] = None,
      val xs: Option[Ratios] = None
  ) {
    def withAll(r: Ratios) = withLg(r).withMd(r).withSm(r).withXs(r)

    def withLg(lg: Ratios): ScreenRatios = copy(lg = lg)

    def withMd(md: Option[Ratios]): ScreenRatios = copy(md = md)
    def withMd(md: Ratios): ScreenRatios         = withMd(Option(md))

    def withSm(sm: Option[Ratios]): ScreenRatios = copy(sm = sm)
    def withSm(sm: Ratios): ScreenRatios         = withSm(Option(sm))

    def withXs(xs: Option[Ratios]): ScreenRatios = copy(xs = xs)
    def withXs(xs: Ratios): ScreenRatios         = withXs(Option(xs))

    private def copy(
        lg: Ratios = lg,
        md: Option[Ratios] = md,
        sm: Option[Ratios] = sm,
        xs: Option[Ratios] = xs
    ) = new ScreenRatios(lg, md, sm, xs)
  }

  object ScreenRatios:
    // Same on all screens
    // lg is not optional, need to have at least one ratio...
    val default: ScreenRatios = ScreenRatios(
      Ratios.default,
      Option(Ratios.default),
      Option(Ratios.default),
      Option(Ratios.default)
    )

}
