package docs.hepek

import scalatags.Text.all._
import utils.Imports.Bundle._
import utils.Imports._
import utils._

object StaticPage extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Static page")

  override def blogSettings = super.blogSettings.withSections(basicSettingsSection)

  val staticSiteSettingsProps = List(
    ClassProperty("indexPage", "Option[StaticPage]", "Home page", Some("None")),
    ClassProperty(
      "mainPages",
      "List[StaticPage]",
      "Pages to display in navbar",
      Some("List.empty")
    )
  )

  /* CONTENT */
  val basicSettingsSection = Section(
    "Basic settings",
    frag(
      s"""
        When you extend [`StaticPage`](${links.StaticPageUrl}) you get support for a static HTML page.  
        For basic settings you override `def staticSiteSettings`. It has the following fields:
      """.md,
      renderClassProps(staticSiteSettingsProps),
      "You usually define it in a common trait, for example:".md,
      chl.scala("""
        trait MyStaticPageTemplate extends StaticPage {
          override def staticSiteSettings =
            super.staticSiteSettings
              .withIndexPage(site.Index)
        }
      """),
      "Example of page definition:",
      chl.scala("""
        package site
        
        object Index extends MyStaticPageTemplate {
          override def pageContent = div("content here")
        }
      """),
      """
        You can get a relative link to `Index` page with `ref` method.  
        E.g. if you have another page in `site.posts` package (notice that `Index` is in the `site` package), 
          `Index.ref` will give you a string "../index.html"
      """.md
    )
  )
}
