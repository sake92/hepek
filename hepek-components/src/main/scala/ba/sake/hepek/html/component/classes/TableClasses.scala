package ba.sake.hepek.html.component.classes

import scalatags.Text.all._

trait TableClasses {

  def tableClass: AttrPair
  def tableStriped: AttrPair
  def tableBordered: AttrPair
  def tableHoverable: AttrPair
  def tableResponsive: AttrPair

  def tableRowSuccess: AttrPair
  def tableRowInfo: AttrPair
  def tableRowWarning: AttrPair
  def tableRowDanger: AttrPair
  def tableRowActive: AttrPair

  def tableDataSuccess: AttrPair
  def tableDataInfo: AttrPair
  def tableDataWarning: AttrPair
  def tableDataDanger: AttrPair
  def tableDataActive: AttrPair

}
