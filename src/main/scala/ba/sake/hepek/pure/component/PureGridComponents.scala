package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object PureGridComponents extends PureGridComponents

trait PureGridComponents extends GridComponents {
  import GridComponents._

  private[hepek] override def mkRow(content: Frag*): Frag =
    div(cls := "pure-g")(content)

  private[hepek] override def mkCol2(index: Int, content: List[Frag]): Frag = {
    val classes = halfRatioClasses(index)
    div(cls := classes.mkString(" "))(content)
  }

  private[hepek] override def mkCol3(index: Int, content: List[Frag]): Frag = {
    val classes = thirdRatioClasses(index)
    div(cls := classes.mkString(" "))(content)
  }

  /* HELPERS */
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
