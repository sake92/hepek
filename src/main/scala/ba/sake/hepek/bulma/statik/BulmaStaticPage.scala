package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.{BulmaDependencies, BulmaModifier, Large, Medium}
import ba.sake.hepek.html.structure.StaticPage
import scalatags.Text
import scalatags.Text.all._

trait BulmaStaticPage extends StaticPage with BulmaDependencies {

  import ba.sake.hepek.bulma.component._

  def container(modifiers: BulmaModifier*) = div(cls := s"container${cssClasses(modifiers)}")
  lazy val container                       = div(cls := "container")

  def section       = tag("section")(cls := "section")
  def mediumSection = tag("section")(cls := s"section ${cssClass(Medium)}")
  def largeSection  = tag("section")(cls := s"section ${cssClass(Large)}")

  def footer = tag("footer")(cls := "footer")

}
