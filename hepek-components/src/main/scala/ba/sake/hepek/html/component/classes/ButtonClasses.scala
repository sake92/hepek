package ba.sake.hepek.html.component.classes

import scalatags.Text.all._

trait ButtonClasses {

  def btnClass: AttrPair // base class
  def btnPrimary: AttrPair
  def btnSuccess: AttrPair
  def btnInfo: AttrPair
  def btnWarning: AttrPair
  def btnDanger: AttrPair
  def btnLink: AttrPair
  def btnActive: AttrPair // pressed

  def btnSizeLg: AttrPair
  def btnSizeMd: AttrPair
  def btnSizeSm: AttrPair
  def btnSizeXs: AttrPair

}
