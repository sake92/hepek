package docs

import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import utils.Site
import utils.Imports.Bundle._
import scalatags.Text

object NotFound extends templates.HepekDocsAbstractPage {

  override def pageSettings: PageSettings =
    super.pageSettings.withTitle("Doesn't exist")

  override def fileName = "404.html"

  override def relTo(other: RelativePath): String =
    Site.url + "/" + super.relTo(other)

  override def pageContent: Text.TypedTag[String] =
    div(Classes.txtAlignCenter)(
      s"""
      This page does not exist... :///  
      Click [here](${Site.url}) to go back
      """.md
    )
}
