package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.GridComponents

object BootstrapGridComponents extends BootstrapGridComponents

trait BootstrapGridComponents extends GridComponents {
  import GridComponents._

  def lgRatios: Ratios         = Ratios.Default
  def mdRatios: Option[Ratios] = Option(lgRatios) // follow lg by default...
  def smRatios: Option[Ratios] = Option(lgRatios)
  def xsRatios: Option[Ratios] = Option(lgRatios)

  override def row(content: Frag*) =
    div(cls := "row")(content)

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
    val lg = lgClass(lgRatios.half, index)
    val md = mdRatios.map(r => mdClass(r.half, index))
    val sm = smRatios.map(r => smClass(r.half, index))
    val xs = xsRatios.map(r => xsClass(r.half, index))
    List(Option(lg), md, sm, xs).flatten
  }

  private def thirdRatioClasses(index: Int): List[String] = {
    val lg = lgClass(lgRatios.third, index)
    val md = mdRatios.map(r => mdClass(r.third, index))
    val sm = smRatios.map(r => smClass(r.third, index))
    val xs = xsRatios.map(r => xsClass(r.third, index))
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
