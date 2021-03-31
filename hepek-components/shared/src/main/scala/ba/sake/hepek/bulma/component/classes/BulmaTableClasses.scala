package ba.sake.hepek.bulma.component.classes

import ba.sake.hepek.scalatags.all._
import ba.sake.hepek.html.component.classes.TableClasses
import org.scalajs.dom.Element

trait BulmaTableClasses extends TableClasses {
  override def tableClass: AttrPair[Element,String]      = cls := "table"
  override def tableStriped: AttrPair[Element,String]    = cls := "is-striped"
  override def tableBordered: AttrPair[Element,String]   = cls := "is-bordered"
  override def tableHoverable: AttrPair[Element,String]  = cls := "is-hoverable"
  override def tableResponsive: AttrPair[Element,String] = cls := "table-container" // must wrap table in this...
  override def tableWidthFull: AttrPair[Element,String]  = cls := "is-fullwidth"

  override def tableRowSuccess: AttrPair[Element,String] = cls := "is-success"
  override def tableRowInfo: AttrPair[Element,String]    = cls := "is-info"
  override def tableRowWarning: AttrPair[Element,String] = cls := "is-warning"
  override def tableRowDanger: AttrPair[Element,String]  = cls := "is-danger"
  override def tableRowActive: AttrPair[Element,String]  = cls := "is-active"

  override def tableDataSuccess: AttrPair[Element,String] = cls := "is-success"
  override def tableDataInfo: AttrPair[Element,String]    = cls := "is-info"
  override def tableDataWarning: AttrPair[Element,String] = cls := "is-warning"
  override def tableDataDanger: AttrPair[Element,String]  = cls := "is-danger"
  override def tableDataActive: AttrPair[Element,String]  = cls := "is-active"
}
