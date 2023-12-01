package files.hepek

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object MultiPage extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Multi page").withDescription("Headless CMS, blog, generator")

  override def blogSettings = super.blogSettings.withSections(
    multiPageSection
  )

  val multiPageSection = Section(
    "Multi-page",
    frag(
      s"""
        The abstraction of the `StaticPage` maps nicely to one file.  
        This is a `1:1` relation.  
        But sometimes we need more power. We'd like to render **multiple files** based on *one template*.  
        This template is just a list of `Renderable`s.

        Here's a very simple example:
      """.md,
      chl.scala("""
        import java.nio.file.Paths
        import ba.sake.hepek.scalatags.all._
        import ba.sake.hepek.path.ScalaMultiRenderable
        import examples.Imports._
        
        object MyPages extends ScalaMultiRenderable {
          override def rends =
            List(1, 2, 3)map.(MyPage.apply)
        }
        
        case class MyPage(num: Int) extends StaticPage {
          override def relPath =
            Paths.get(s"pages/page-$num.html")
        
          override def pageContent =
            div(s"This is page number $num")
        }
      """),
      s"""
        This will render 3 pages with paths "pages/page-1.html", "pages/page-2.html" and "pages/page-3.html"
        with their corresponding contents.

        It's a very powerful and nice abstraction.  
        You can browse through an [example of using Wordpress API](${links.WpExampleUrl})
        for rendering a static site.
        
        It makes Hepek SSG a powerful tool for Headles CMS-es. :)  
        Especially since you can use Scala!
      """.md
    )
  )
}
