package docs

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils._, Imports._
import templates.HepekDocsPage

object StaticPage extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Static page")

  override def blogSettings = super.blogSettings.withSections(
    basicSettingsSection,
    bodyContentSection,
    headContentSection
  )

  val siteSettingsProps = List(
    ClassProperty("name", "Option[String]", "Name of this site", Some("None")),
    ClassProperty("indexPage", "Option[StaticPage]", "Home page", Some("None")),
    ClassProperty(
      "mainPages",
      "List[StaticPage]",
      "Pages to display in navbar",
      Some("List.empty")
    ),
    ClassProperty("faviconNormal", "Option[String]", "Favicon of the site", Some("None")),
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
    ClassProperty(
      "category",
      "Option[String]",
      "Page category, used for grouping pages",
      Some("None")
    ),
    ClassProperty("description", "Option[String]", "Description of page content", Some("None"))
  )

  /* CONTENT */
  val basicSettingsSection = Section(
    "Basic settings",
    frag(
      s"""
        When you extend [`StaticPage`](${links.StaticPageUrl}) you get basic support for HTML page.  
        If you want a prettier version, then extend [`BootstrapStaticPage`](${links.BootstrapStaticPageUrl}).  
         
        First we'll define a template for all our static pages.  
        Here we can put site-wide settings, include common JS and CSS dependencies.  
        For basic site settings you override `def siteSettings: SiteSettings`. It has the following fields:
      """.md,
      renderClassProps(siteSettingsProps),
      "`SiteSettings` are usually defined in a common trait, for example:".md,
      chl.scala("""
        trait MySiteTemplate extends BootstrapStaticPage {
          override def siteSettings =
            SiteSettings()
              .withName("example.com")
              .withIndexPage(site.Index)
              .withFaviconNormal(images.ico("favicon").ref)
        }
      """),
      """
        When creating a page implementation (object), you need to implement method `def pageSettings: PageSettings`.  
        For start you can just give it page title, e.g. `override def pageSettings = PageSettings("My page")`.  
        It has the following fields:
      """.md,
      renderClassProps(pageSettingsProps),
      "Example of page definition:",
      chl.scala("""
        package site
        object Index extends MySiteTemplate {
          override def pageSettings =
            PageSettings("Welcome!") // title
              .withDescription("My cool site")
        }
      """),
      """
        You can get a relative link to `Index` page with `ref` method.  
        E.g. if you have a page in `site.posts` package (notice that `Index` is in the `site` package), 
          `Index.ref` will give you a string "../index.html"
      """.md
    )
  )

  val bodyContentSection = Section(
    "Body content",
    s"""
      Next up is the `def bodyContent: List[Frag]` method.  
      You guessed it, it is the `<body>` of your HTML page. :)  
      Here you type Scalatags and get it rendered in the body of your page.  
      By default it is empty list.
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
