package docs

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils.Imports._
import templates.HepekDocsPage

object CodeWalkthrough extends HepekDocsPage {

  override def pageSettings =
    PageSettings("Code walkthrough")
      .withDescription("Code walkthrough")

  override def blogSettings = super.blogSettings.withSections(
    Section("Contents", contentContent),
    Section("Templates", templatesContent),
    Section("Common imports", importsContent),
  )

  /* CONTENT */
  val contentContent = frag(
    md("""
          Content `object`s are usually placed in the `site` package.  
          This means they are rendered to the `/site` folder.  
          So, package is treated like a folder!

          File name is derived from `object`'s name, snake-cased, lowercased.  
          Also, some characters illegal for URLs are removed, for obvious reasons. :p  
          E.g. `object HelloWorld` will have name "hello-world.html" (default extension is "html").
        """)
  )

  val templatesContent = frag(
    md("""
      Template `trait`s are usually placed in the `templates` package.  
      You can put these *wherever you prefer*, only the content object packages matter.
    """)
  )

  val importsContent = frag(
    md("""
      `Imports` object from `utils` package is recommended way for importing common components.  
      It's easier to change stuff just there, at one place.  
      E.g. if you want to change code highlight settings, you would override methods in `chl` object.
    """)
  )

}
