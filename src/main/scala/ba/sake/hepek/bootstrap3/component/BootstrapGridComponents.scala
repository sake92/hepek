package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object BootstrapGridComponents extends BootstrapGridComponents

trait BootstrapGridComponents extends GridComponents {
  import GridComponents._

  override def mkRow(content: Frag*): Frag =
    div(cls := "row")(content)

  override def mkCol2(index: Int, content: List[Frag]): Frag = {
    val classes = halfRatioClasses(index)
    div(cls := classes.mkString(" "))(content)
  }

  override def mkCol3(index: Int, content: List[Frag]): Frag = {
    val classes = thirdRatioClasses(index)
    div(cls := classes.mkString(" "))(content)
  }

  /* HELPERS */
  private def halfRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.half, index)
    val md = screenRatios.md.map(r => mdClass(r.half, index))
    val sm = screenRatios.sm.map(r => smClass(r.half, index))
    val xs = screenRatios.xs.map(r => xsClass(r.half, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def thirdRatioClasses(index: Int): List[String] = {
    val lg = lgClass(screenRatios.lg.third, index)
    val md = screenRatios.md.map(r => mdClass(r.third, index))
    val sm = screenRatios.sm.map(r => smClass(r.third, index))
    val xs = screenRatios.xs.map(r => xsClass(r.third, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def xsClass(ratio: Ratio, index: Int): String =
    "col-xs-" + ratioValue(ratio, index)

  private def smClass(ratio: Ratio, index: Int): String =
    "col-sm-" + ratioValue(ratio, index)

  private def mdClass(ratio: Ratio, index: Int): String =
    "col-md-" + ratioValue(ratio, index)

  private def lgClass(ratio: Ratio, index: Int): String =
    "col-lg-" + ratioValue(ratio, index)

  private def ratioValue(ratio: Ratio, index: Int): Int =
    ratio2BS(ratio.values(index), ratio.values)

  // for args 1,1:2 => (1/3)*12 == 4
  private def ratio2BS(ratio: Int, allRatios: List[Int]): Int =
    ((ratio / allRatios.sum.toDouble) * 12).toInt
}
