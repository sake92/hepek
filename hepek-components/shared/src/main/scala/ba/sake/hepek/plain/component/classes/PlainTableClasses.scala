package ba.sake.hepek.plain.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TableClasses
import org.scalajs.dom.Element

trait PlainTableClasses extends TableClasses {
  override def tableClass: AttrPair[Element,String]      = cls := "table"
  override def tableStriped: AttrPair[Element,String]    = cls := "table-striped"
  override def tableBordered: AttrPair[Element,String]   = cls := "table-bordered"
  override def tableHoverable: AttrPair[Element,String]  = cls := "table-hover"
  override def tableResponsive: AttrPair[Element,String] = cls := "table-responsive"
  override def tableWidthFull: AttrPair[Element,String]  = cls := "table-fullwidth"

  override def tableRowSuccess: AttrPair[Element,String] = cls := "success"
  override def tableRowInfo: AttrPair[Element,String]    = cls := "info"
  override def tableRowWarning: AttrPair[Element,String] = cls := "warning"
  override def tableRowDanger: AttrPair[Element,String]  = cls := "danger"
  override def tableRowActive: AttrPair[Element,String]  = cls := "active"

  override def tableDataSuccess: AttrPair[Element,String] = cls := "success"
  override def tableDataInfo: AttrPair[Element,String]    = cls := "info"
  override def tableDataWarning: AttrPair[Element,String] = cls := "warning"
  override def tableDataDanger: AttrPair[Element,String]  = cls := "danger"
  override def tableDataActive: AttrPair[Element,String]  = cls := "active"
}
