package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object PureGridComponents extends PureGridComponents

trait PureGridComponents extends GridComponents {
  import GridComponents._

  override def row(content: Frag*) =
    div(cls := "pure-g")(content)

  override def row(half1: Half1, half2: Half2) =
    row(half1.content ++ half2.content)

  override def row(third1: Third1, third2: Third2, third3: Third3) =
    row(third1.content ++ third2.content ++ third3.content)

  // HALF
  override def half1(content: Frag*) = {
    val classes = halfRatioClasses(0)
    val c       = div(cls := classes.mkString(" "))(content)
    Half1(List(c))
  }

  override def half2(content: Frag*) = {
    val classes = halfRatioClasses(1)
    val c       = div(cls := classes.mkString(" "))(content)
    Half2(List(c))
  }

  // THIRD
  override def third1(content: Frag*) = {
    val classes = thirdRatioClasses(0)
    val c       = div(cls := classes.mkString(" "))(content)
    Third1(List(c))
  }

  override def third2(content: Frag*) = {
    val classes = thirdRatioClasses(1)
    val c       = div(cls := classes.mkString(" "))(content)
    Third2(List(c))
  }

  override def third3(content: Frag*) = {
    val classes = thirdRatioClasses(2)
    val c       = div(cls := classes.mkString(" "))(content)
    Third3(List(c))
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
    ratio2BS(ratio.values(index), ratio.values)

  // for args 1,1:2 => (1/3)*24 == 8
  private def ratio2BS(ratio: Int, allRatios: List[Int]): Int =
    ((ratio / allRatios.sum.toDouble) * 24).toInt
}
