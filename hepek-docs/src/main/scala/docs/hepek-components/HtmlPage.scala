package files.hepek.components

import utils._
import utils.Imports._
import utils.Imports.Bundle.*, Tags.*

object HtmlPage extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Html page")

  override def blogSettings = super.blogSettings.withSections(
    basicSettingsSection,
    bodyContentSection,
    headContentSection
  )

  val siteSettingsProps = List(
    ClassProperty("name", "Option[String]", "Name of this site", Some("None")),
    ClassProperty("faviconNormal", "Option[String]", "Favicon of this site", Some("None")),
    ClassProperty(
      "faviconInverted",
      "Option[String]",
      "Favicon with alternative color, can be used in navbar for example",
      Some("None")
    )
  )

  val pageSettingsProps = List(
    ClassProperty("title", "String", "Title of this page", Some("changeme")),
    ClassProperty("label", "String", "Label used for link to this page", Some("<same as title>")),
    ClassProperty("language", "String", "Language of this page", Some("en")),
    ClassProperty("description", "Option[String]", "Description of page content", Some("None"))
  )

  /* CONTENT */
  val basicSettingsSection = Section(
    "Basic settings",
    frag(
      s"""
        When you extend the [`HtmlPage`](${links.HtmlPageUrl}) you get basic support for a HTML page.  
         
        First we'll define a template for all our pages.  
        Here we can put site-wide settings, include common JS and CSS dependencies.  
        For basic site settings you override `def siteSettings`. It has the following fields:
      """.md,
      renderClassProps(siteSettingsProps),
      "`SiteSettings` are usually defined in a common trait, for example:".md,
      chl.scala("""
        trait MyPageTemplate extends HtmlPage {
          override def siteSettings =
            super.siteSettings
              .withName("example.com")
              .withFaviconNormal("favicon.jpg")
        }
      """),
      """
        ---
        Per-page settings are in the `def pageSettings`.  
        `PageSettings` has the following fields:
      """.md,
      renderClassProps(pageSettingsProps),
      "Example of page definition:",
      chl.scala("""
        package site
        
        object Index extends MyPageTemplate {
          override def pageSettings =
            super.pageSettings
              .withTitle("Welcome!")
              .withDescription("My cool site")
        }
      """)
    )
  )

  val bodyContentSection = Section(
    "Body content",
    s"""
      Next up is the `def bodyContent: List[Frag]` method.  
      You guessed it, it is the `<body>` of your HTML page. :)  
      Here you type content to be rendered in the body of your page.  
    """.md
  )

  val headContentSection = Section(
    "Head content",
    s"""
      Next up is the `def headContent: List[Frag]` method.  
      Yup, it is the `<head>` of your HTML page.  
      It has some defaults, you can see them in source code or inspect it in browser.
    """.md
  )
}
