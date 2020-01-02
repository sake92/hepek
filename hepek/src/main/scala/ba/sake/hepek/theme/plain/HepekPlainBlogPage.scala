package ba.sake.hepek.theme.plain

import scalatags.Text.all._
import ba.sake.hepek.plain.statik.PlainStaticPage
import ba.sake.hepek.plain.component.PlainGridComponents
import ba.sake.hepek.html.statik.BlogPostPage
import ba.sake.hepek.html.statik.Section

trait HepekPlainBlogPage extends BlogPostPage with PlainStaticPage {
  // avoid polluting user's namespace
  import PlainGridComponents._

  // TODO add header

  override def pageContent: Frag =
    frag(
      mkRow(
        div(cls := "col-lg-2 col-md-3  hidden-print")(
          renderSidebar
        ),
        div(cls := "col-lg-6 col-md-6")(
          div(cls := "hidden-print")(
            blogSettings.createDate.map(cd => div(cd.format(blogSettings.dateFormat))),
            blogSettings.author.map(author => div(author))
          ),
          tag("article")(renderTocAndSections(blogSettings.sections))
        )
      )
    )

  /* CONTENT*/
  private def renderTocAndSections(secs: List[Section]): Frag =
    renderSections(secs)

  // related pages
  private def renderSidebar: Frag = {
    val pageLiTags = for {
      p <- categoryPosts
      activeClass = if (p.relPath == relPath) "active" else ""
    } yield li(cls := activeClass, a(href := p.ref)(p.pageSettings.label))
    tag("nav")(
      ul(pageLiTags)
    )
  }

  private def renderSections(secs: List[Section], depth: Int = 2): List[Frag] =
    secs.map { s =>
      val hTag = tag("h" + (depth + 1))
      tag("section")(
        hTag(id := s.id)(s.name),
        s.content,
        renderSections(s.children, depth + 1)
      )
    }
}
