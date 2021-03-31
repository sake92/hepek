package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TableClasses

trait BulmaTableClasses extends TableClasses {
  override def tableClass      = cls := "table"
  override def tableStriped    = cls := "is-striped"
  override def tableBordered   = cls := "is-bordered"
  override def tableHoverable  = cls := "is-hoverable"
  override def tableResponsive = cls := "table-container" // must wrap table in this...
  override def tableWidthFull  = cls := "is-fullwidth"

  override def tableRowSuccess = cls := "is-success"
  override def tableRowInfo    = cls := "is-info"
  override def tableRowWarning = cls := "is-warning"
  override def tableRowDanger  = cls := "is-danger"
  override def tableRowActive  = cls := "is-active"

  override def tableDataSuccess = cls := "is-success"
  override def tableDataInfo    = cls := "is-info"
  override def tableDataWarning = cls := "is-warning"
  override def tableDataDanger  = cls := "is-danger"
  override def tableDataActive  = cls := "is-active"
}
