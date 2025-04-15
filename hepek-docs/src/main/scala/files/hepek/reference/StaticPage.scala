package files.hepek.reference

import scalatags.Text.all.*
import utils.Imports.*

object StaticPage extends HepekReferencePage {

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
        When you extend [`StaticPage`](${utils.links.StaticPageUrl}) you get support for a static HTML page.  
        For basic settings you can override `def staticSiteSettings`. It has the following fields:
      """.md,
      renderClassProps(staticSiteSettingsProps),
      """
        You usually define it in a common trait, for example:

        ```scala
        package utils
        import Bundle.*

        trait MyStaticPageTemplate extends StaticPage {
          override def staticSiteSettings = super.staticSiteSettings
            .withIndexPage(files.Index)
        }
        ```
        
        Example of a page definition:
        ```scala
        package files
        import utils.*
        import Bundle.*. Tags.*

        object Index extends MyStaticPageTemplate {
          override def mainContent = div("content here")
        }
        ```
      
        ---
        You can get a relative link to `Index` page with the `ref` method.  
        E.g. if you have another page in `files.posts` package,
        `Index.ref` will give you a string "../index.html"
      """.md
    )
  )
}
