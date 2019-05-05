package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma._
import scalatags.Text
import scalatags.Text.all.{content, _}

object BreadcrumbComponents extends BreadcrumbComponents

case class BreadcrumbItem(label: String) extends BulmaElement {
  override def content: Text.all.Frag = li(a(label))
}

trait BreadcrumbComponents {

  def breadcrumb(content: BreadcrumbItem*)         = customBreadcrumb(List())(content: _*)
  def centeredBreadcrumb(content: BreadcrumbItem*) = customBreadcrumb(List(Centered))(content: _*)
  def leftBreadcrumb(content: BreadcrumbItem*)     = customBreadcrumb(List(Left))(content: _*)
  def rightBreadcrumb(content: BreadcrumbItem*)    = customBreadcrumb(List(Right))(content: _*)

  def smallBreadcrumb(content: BreadcrumbItem*)  = customBreadcrumb(List(Small))(content: _*)
  def mediumBreadcrumb(content: BreadcrumbItem*) = customBreadcrumb(List(Medium))(content: _*)
  def largeBreadcrumb(content: BreadcrumbItem*)  = customBreadcrumb(List(Large))(content: _*)

  def customBreadcrumb(attributes: List[BulmaModifier])(items: BreadcrumbItem*) =
    tag("nav")(cls := enrichCssClasses("breadcrumb", attributes: _*))(ul(items.map(_.content)))

}
