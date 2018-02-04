package ba.sake.hepek.bootstrap3.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.BasicComponents

object BootstrapBasicComponents extends BootstrapBasicComponents

trait BootstrapBasicComponents extends BasicComponents {

  override def hyperlink(hreff: String, newWindow: Boolean = false) = {
    val optParams = if (newWindow) List(target := "_blank") else List()
    a(href := hreff, optParams)
  }

}
