package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import hepek.templates.HepekDocsPage
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._

object CodeWalkthrough extends HepekDocsPage {

  override def pageTitle       = "Code walkthrough"
  override def pageDescription = Option("Code walkthrough")

  override def postSections = List(
    Section("Contents", contentContent),
    Section("Templates", templatesContent),
    Section("Common imports", importsContent),
  )

  /* CONTENT */
  val contentContent = frag(
    md("""
          Contents (`object`s) are in the `site` package.  
          This also means that they are rendered to the `/site` folder!  
          So, package matters! Use them just like a folder tree.

          File names are derived from `object`'s name, snake-cased, lowercased.  
          Also, some characters illegal for URLs are removed, for obvious reasons. :p  
          E.g. `object HelloWorld` will have name "hello-world.html" (default extension is "html").
        """)
  )

  val templatesContent = frag(
    md("""
        Templates (`trait`s) are in the `templates` package.  
        You can put these *wherever you prefer*, only the content object packages matter.
        """)
  )

  val importsContent = frag(
    md("""
        `Imports` object from `utils` package is recommended way for importing common components.  
        It's easier to change stuff just there, at one place.  
        E.g. if you want to change code highlight settings, you would override methods in `chl` object. :)
        """)
  )

}
