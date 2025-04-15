package files

import ba.sake.hepek.core.RelativePath
import utils.Site

object NotFound extends templates.HepekDocsAbstractPage {

  override def pageSettings =
    super.pageSettings.withTitle("Doesn't exist")

  override def fileName = "404.html"

  override def relTo(other: RelativePath) =
    Site.url + "/" + super.relTo(other)

  override def mainContent =
    s"""
    This page does not exist... :///  
    Click [here](${Site.url}) to go back
    """.md

}
