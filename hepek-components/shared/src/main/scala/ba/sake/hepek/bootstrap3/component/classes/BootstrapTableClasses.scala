package ba.sake.hepek.bootstrap3.component.classes

import ba.sake.hepek.html.component.classes.TableClasses
import ba.sake.hepek.scalatags.all._

trait BootstrapTableClasses extends TableClasses {
  override def tableClass: AttrPair      = cls := "table"
  override def tableStriped: AttrPair    = cls := "table-striped"
  override def tableBordered: AttrPair   = cls := "table-bordered"
  override def tableHoverable: AttrPair  = cls := "table-hover"
  override def tableResponsive: AttrPair = cls := "table-responsive"
  override def tableWidthFull: AttrPair  = cls := ""

  override def tableRowSuccess: AttrPair = cls := "success"
  override def tableRowInfo: AttrPair    = cls := "info"
  override def tableRowWarning: AttrPair = cls := "warning"
  override def tableRowDanger: AttrPair  = cls := "danger"
  override def tableRowActive: AttrPair  = cls := "active"

  override def tableDataSuccess: AttrPair = cls := "success"
  override def tableDataInfo: AttrPair    = cls := "info"
  override def tableDataWarning: AttrPair = cls := "warning"
  override def tableDataDanger: AttrPair  = cls := "danger"
  override def tableDataActive: AttrPair  = cls := "active"
}
