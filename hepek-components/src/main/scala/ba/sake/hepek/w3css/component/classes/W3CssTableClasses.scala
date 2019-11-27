package ba.sake.hepek.w3css.component.classes

import scalatags.Text.all._
import ba.sake.hepek.html.component.classes.TableClasses

object W3CssTableClasses extends W3CssTableClasses

trait W3CssTableClasses extends TableClasses {
  override def tableClass      = cls := "w3-table"
  override def tableStriped    = cls := "w3-striped"
  override def tableBordered   = cls := "w3-bordered"
  override def tableHoverable  = cls := "w3-hoverable"
  override def tableResponsive = cls := "w3-responsive"

  override def tableRowSuccess = cls := "w3-green"
  override def tableRowInfo    = cls := "w3-blue"
  override def tableRowWarning = cls := "w3-yellow"
  override def tableRowDanger  = cls := "w3-red"
  override def tableRowActive  = cls := "active"

  override def tableDataSuccess = cls := "w3-green"
  override def tableDataInfo    = cls := "w3-blue"
  override def tableDataWarning = cls := "w3-yellow"
  override def tableDataDanger  = cls := "w3-red"
  override def tableDataActive  = cls := "active"
}
