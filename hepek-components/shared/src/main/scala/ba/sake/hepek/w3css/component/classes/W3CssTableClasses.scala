package ba.sake.hepek.w3css.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TableClasses
import org.scalajs.dom.Element

trait W3CssTableClasses extends TableClasses {
  override def tableClass: AttrPair[Element,String]      = cls := "w3-table"
  override def tableStriped: AttrPair[Element,String]    = cls := "w3-striped"
  override def tableBordered: AttrPair[Element,String]   = cls := "w3-bordered"
  override def tableHoverable: AttrPair[Element,String]  = cls := "w3-hoverable"
  override def tableResponsive: AttrPair[Element,String] = cls := "w3-responsive"
  override def tableWidthFull: AttrPair[Element,String]  = cls := ""

  override def tableRowSuccess: AttrPair[Element,String] = cls := "w3-green"
  override def tableRowInfo: AttrPair[Element,String]    = cls := "w3-blue"
  override def tableRowWarning: AttrPair[Element,String] = cls := "w3-yellow"
  override def tableRowDanger: AttrPair[Element,String]  = cls := "w3-red"
  override def tableRowActive: AttrPair[Element,String]  = cls := "active"

  override def tableDataSuccess: AttrPair[Element,String] = cls := "w3-green"
  override def tableDataInfo: AttrPair[Element,String]    = cls := "w3-blue"
  override def tableDataWarning: AttrPair[Element,String] = cls := "w3-yellow"
  override def tableDataDanger: AttrPair[Element,String]  = cls := "w3-red"
  override def tableDataActive: AttrPair[Element,String]  = cls := "active"
}
