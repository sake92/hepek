package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import hepek.templates.HepekDocsPage
import hepek.utils._, Imports._

object StaticPage extends HepekDocsPage {

  override def pageTitle = "Static page"

  override def postSections = List(
    basicSettingsSection,
    bodyContentSection,
    headContentSection
  )

  val siteSettingsProps = List(
    ClassProperty("name", "String", "Name of the site"),
    ClassProperty("indexPage", "StaticPage", "Home page"),
    ClassProperty("mainPages",
                  "List[StaticPage] ",
                  "Pages to display in navbar",
                  Some("List.empty")),
    ClassProperty("faviconNormal",
                  "Option[String]",
                  "Favicon of the site",
                  Some("None")),
    ClassProperty(
      "faviconInverted",
      "Option[String]",
      "Favicon with alternative color, can be used in navbar for example",
      Some("None")
    )
  )

  /* CONTENT */
  val basicSettingsSection = Section(
    "Basic settings",
    frag(
      md(s"""
        When you extend [`StaticPage`](${links.StaticPageUrl}) you get basic support for HTML page.  
        You must implement the following methods:
        - `def pageTitle: String` so that your page has a title
        - `def siteSettings: SiteSettings` so that your page has a Home button

        The page will be blank, but we need to start somewhere.  
        If you want it to be a bit prettier, then extend [`BootstrapStaticPage`](${links.BootstrapStaticPageUrl}).  
        `SiteSettings` has the following fields:
      """),
      renderClassProps(siteSettingsProps),
      md("`SiteSettings` are usually defined in a common trait, for example:"),
      chl.scala("""
        trait MySiteTemplate extends StaticSite {
          override def siteSettings = SiteSettings(
            "example.com",
            site.Index,
            List(site.MyPage),
            Some(Images.favicon.ref)
          )
        }
      """),
      md(
        "Then you just extend this one and all your pages have same settings. :)"
      )
    )
  )

  val bodyContentSection = Section(
    "Body content",
    frag(
      md(s"""
        Next up is the `def bodyContent: List[Frag]` method.  
        You guessed it, it is the `<body>` of your HTML page. :)  
        Here you type Scalatags and get it rendered in the body of your page.  
        By default it is empty list.
      """)
    )
  )

  val headContentSection = Section(
    "Head content",
    frag(
      md(s"""
        Next up is the `def headContent: List[Frag]` method.  
        Yup, it is the `<head>` of your HTML page.  
        It has some defaults, you can see them in source code or inspect it in browser.
      """)
    )
  )

}
