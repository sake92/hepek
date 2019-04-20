package ba.sake.hepek.bulma.statik

import ba.sake.hepek.bulma.{BulmaDependencies, BulmaModifier, Large, Medium}
import ba.sake.hepek.html.structure.StaticPage
import scalatags.Text.all._

trait BulmaStaticPage extends StaticPage with BulmaDependencies {

  import ba.sake.hepek.bulma.component._

  def container(modifiers: BulmaModifier*) = div(cls := enrichCssClasses("container", modifiers))
  lazy val container                       = div(cls := "container")

  def section       = tag("section")(cls := "section")
  def mediumSection = tag("section")(cls := s"section ${Medium.classname}")
  def largeSection  = tag("section")(cls := s"section ${Large.classname}")

  def footer = tag("footer")(cls := "footer")

}
