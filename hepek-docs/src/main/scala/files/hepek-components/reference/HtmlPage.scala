package files.hepek.components.reference

import scalatags.Text.all.*
import utils.Imports.*

object HtmlPage extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings.withTitle("Html page")

  override def blogSettings = super.blogSettings.withSections(
    basicSettingsSection,
    pageContentSection,
    metaSettingsSection,
    bodyContentSection,
    headContentSection,
    manifestSection
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
        When you extend `Page` from your favorite bundle, you get basic support for a HTML page.  
        It is recommended to make a common template that `extends Page`.  
        Here you put site-wide settings, include common JS and CSS dependencies.  
        For basic site settings you override `def siteSettings`. It has the following fields:
      """.md,
      renderClassProps(siteSettingsProps),
      """
         `SiteSettings` are usually defined in a common trait, for example:
       ```scala
        package utils

        import Imports.Bundle.*

        trait MyPageTemplate extends Page {
          override def siteSettings = super.siteSettings
            .withName("example.com")
            .withFaviconNormal("favicon.jpg")
        }
        ```
        ---
        Per-page settings are in the `def pageSettings`.  
        `PageSettings` has the following fields:
      """.md,
      renderClassProps(pageSettingsProps),
      """
         Example of page definition:
        ```scala
        package files
        import utils.*
        
        object Index extends MyPageTemplate {
          override def pageSettings =
            super.pageSettings
              .withTitle("Welcome!")
              .withDescription("My cool site")
        }
        ```
      """.md
    )
  )

  val pageContentSection = Section(
    "Page content",
    s"""
      Next up is the `def pageContent: Frag` method.  
      Here is the content of your HTML page.  
      It will be embedded somewhere in the `<body>`, depending on your framework.
    """.md
  )

  val metaSettingsSection = Section(
    "Meta content",
    s"""
    The `def metaSettings` defines all things `<meta>`:
    - charset
    - viewport
    - og tags
    - etc
    """.md
  )

  val bodyContentSection = Section(
    "Body content",
    s"""
      If you really, really want to redefine content of the `<body>`, use the `def bodyContent: Frag` method.  
      Note that none of the `<script>`s will be included, you have to do all by yourself!  
      You can peek at the original implementation, copy-paste it and tweak it a bit.
    """.md
  )

  val headContentSection = Section(
    "Head content",
    s"""
      If you really, really want to redefine content of `<head>`, use the `def headContent: Frag` method.  
      Note that none of the `<meta>`, `<title>`.. will be included, you have to all by yourself!
    """.md
  )

  val manifestSection = Section(
    "Manifest",
    s"""
      The `def manifest` contains the `manifest` of your website, it is useful for PWAs (Progressive Web Apps).  
      You can serialize it like this:
      ```scala
      import ba.sake.tupson.*
      
      println(MyPage.manifest.toJson)
      ```
    """.md
  )
}
