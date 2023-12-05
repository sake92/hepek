package ba.sake.hepek.pure.component

import ba.sake.hepek.html.component.GridComponents
import ba.sake.hepek.html.component.GridComponents.ScreenRatios
import ba.sake.hepek.scalatags.all._

final class PureGridComponents(
    val screenRatios: ScreenRatios = GridComponents.DefaultScreenRatios
) extends GridComponents {
  import GridComponents._

  def withScreenRatios(screenRatios: ScreenRatios): PureGridComponents =
    new PureGridComponents(screenRatios)

  protected override def mkRow(content: Frag*): Frag =
    div(cls := "pure-g")(content)

  protected override def mkRowSingleCol(content: Seq[Frag]): Frag = {
    val classes1 = singleRatioClasses(0).map(cls := _)
    val classes2 = singleRatioClasses(1).map(cls := _)
    val classes3 = singleRatioClasses(2).map(cls := _)
    mkRow(
      div(classes1),
      div(classes2)(content),
      div(classes3)
    )
  }

  protected override def mkCol2(index: Int, content: Col2): Frag = {
    val classes = halfRatioClasses(index).map(cls := _)
    div(classes)(content.content)
  }

  protected override def mkCol3(index: Int, content: Col3): Frag = {
    val classes = thirdRatioClasses(index).map(cls := _)
    div(classes)(content.content)
  }

  /* HELPERS */
  private def singleRatioClasses(index: Int): List[String] = {
    // "pure-u-1" is added ALWAYS, emulates default div stacking... ?
    val lg = lgClass(screenRatios.lg.single, index)
    val md = screenRatios.md.map(r => mdClass(r.single, index))
    val sm = screenRatios.sm.map(r => smClass(r.single, index))
    val xs = screenRatios.xs.map(r => xsClass(r.single, index))
    List(Option(lg), md, sm, xs, Option("pure-u-1")).flatten
  }

  private def halfRatioClasses(index: Int): List[String] = {
    // "pure-u-1" is added ALWAYS, emulates default div stacking... ?
    val lg = lgClass(screenRatios.lg.half, index)
    val md = screenRatios.md.map(r => mdClass(r.half, index))
    val sm = screenRatios.sm.map(r => smClass(r.half, index))
    val xs = screenRatios.xs.map(r => xsClass(r.half, index))
    List(Option(lg), md, sm, xs, Option("pure-u-1")).flatten
  }

  private def thirdRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.third, index)
    val md = screenRatios.md.map(r => mdClass(r.third, index))
    val sm = screenRatios.sm.map(r => smClass(r.third, index))
    val xs = screenRatios.xs.map(r => xsClass(r.third, index))
    List(Option(lg), md, sm, xs, Option("pure-u-1")).flatten
  }

  private def xsClass(ratio: Ratio, index: Int): String =
    "pure-u-xs-" + ratioValue(ratio, index) + "-24"

  private def smClass(ratio: Ratio, index: Int): String =
    "pure-u-sm-" + ratioValue(ratio, index) + "-24"

  private def mdClass(ratio: Ratio, index: Int): String =
    "pure-u-md-" + ratioValue(ratio, index) + "-24"

  private def lgClass(ratio: Ratio, index: Int): String =
    "pure-u-lg-" + ratioValue(ratio, index) + "-24"

  private def ratioValue(ratio: Ratio, index: Int): Int =
    ratio2Pure(ratio.values(index), ratio.values)

  // for args 1,1:2 => (1/3)*24 == 8
  private def ratio2Pure(ratio: Int, allRatios: List[Int]): Int =
    ((ratio / allRatios.sum.toDouble) * 24).toInt

}
