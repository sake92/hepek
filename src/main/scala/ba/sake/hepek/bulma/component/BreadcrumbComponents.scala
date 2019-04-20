package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma._
import scalatags.Text.all._

object BreadcrumbComponents extends BreadcrumbComponents

trait BreadcrumbComponents {

  def breadcrumb(content: Frag*)         = customBreadcrumb(List())(content)
  def centeredBreadcrumb(content: Frag*) = customBreadcrumb(List(Centered))(content)
  def leftBreadcrumb(content: Frag*)     = customBreadcrumb(List(Left))(content)
  def rightBreadcrumb(content: Frag*)    = customBreadcrumb(List(Right))(content)

  def smallBreadcrumb(content: Frag*)  = customBreadcrumb(List(Small))(content)
  def mediumBreadcrumb(content: Frag*) = customBreadcrumb(List(Medium))(content)
  def largeBreadcrumb(content: Frag*)  = customBreadcrumb(List(Large))(content)

  def customBreadcrumb(attributes: List[BulmaModifier])(content: Frag*) =
    tag("nav")(cls := s"breadcrumb${cssClasses(attributes)}")(ul(for {
      elem <- content
    } yield li(elem)))

}
