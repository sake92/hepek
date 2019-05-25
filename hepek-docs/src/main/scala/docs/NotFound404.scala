package docs

import scalatags.Text.all._
import ba.sake.hepek.core.RelativePath
import utils.Site
import utils.Imports._

object NotFound extends templates.HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Doesn't exist")

  override def fileName = "404.html"

  override def relTo(other: RelativePath) =
    Site.url + "/" + super.relTo(other)

  override def pageContent = div(cls := "text-center")(
    """
      This page does not exist... :/  
      Click 
    """.md,
    hyperlink(Site.url)("here"),
    " to go back".md
  )

}
