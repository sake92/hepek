package ba.sake.hepek.html.component.classes

import ba.sake.hepek.scalatags.all.*

private[hepek] trait TextClasses:

  def txtPrimary: AttrPair
  def txtSuccess: AttrPair
  def txtInfo: AttrPair
  def txtWarning: AttrPair
  def txtDanger: AttrPair

  def txtAlignLeft: AttrPair
  def txtAlignCenter: AttrPair
  def txtAlignRight: AttrPair
  def txtAlignJustify: AttrPair

  def txtLowercase: AttrPair
  def txtUppercase: AttrPair
  def txtCapitalize: AttrPair
