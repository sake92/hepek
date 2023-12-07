package ba.sake.hepek.theme.plain

import ba.sake.hepek.html.statik.BlogPostPage
import ba.sake.hepek.html.statik.Section
import ba.sake.hepek.plain.statik.PlainStaticBundle
import ba.sake.hepek.scalatags.all.*

object HepekPlainBlogPage {
  val Bundle = PlainStaticBundle.default
}
import HepekPlainBlogPage.Bundle.*

trait HepekPlainBlogPage extends BlogPostPage with StaticPage {
  import Grid.*

  // TODO add header

  override def pageContent: Frag =
    frag(
      row(
        div(cls := "col-lg-2 col-md-3  hidden-print")(
          renderSidebar
        ),
        div(cls := "col-lg-6 col-md-6")(
          div(cls := "hidden-print")(
            blogSettings.createdDate.map(cd => div(cd.format(blogSettings.dateFormat))),
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
