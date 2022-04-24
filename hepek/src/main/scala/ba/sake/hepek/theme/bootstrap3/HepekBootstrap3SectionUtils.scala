package ba.sake.hepek.theme.bootstrap3

import ba.sake.hepek.html.statik.Section
import ba.sake.hepek.scalatags.all._

private[bootstrap3] object HepekBootstrap3SectionUtils {

  /** Renders the sections. */
  def renderSections(secs: List[Section], depth: Int = 2): List[Frag] =
    secs.map { s =>
      // h2, h3...
      val hTag = tag("h" + depth)
      tag("section")(
        hTag(id := s.id)(s.name),
        s.content,
        renderSections(s.children, depth + 1)
      )
    }

  /** Renders the togglable TOC (Table of Contents). */
  def togglableTOC(tocTitle: String, secs: List[Section]): Frag =
    div(cls := "panel-group hidden-print")(
      div(cls := "panel panel-default")(
        div(cls := "panel-heading")(
          h4(cls := "panel-title")(
            a(data.toggle := "collapse", href := "#collapseTOC")(tocTitle)
          )
        ),
        div(id := "collapseTOC", cls := "panel-collapse collapse")(
          div(cls := "panel-body pages-toc")(renderTogglableTOC(secs))
        )
      )
    )

  /** Renders the scrollable TOC (Table of Contents). */
  def renderScrollspyTOC(secs: List[Section]): Frag =
    tag("nav")(id := "tocScrollspy")(
      renderScrollspyTocUl(secs)
    )

  /* helpers */
  private def renderTogglableTOC(
      secs: List[Section],
      maxDepth: Int = 2,
      depth: Int = 1
  ): List[Frag] =
    if (depth > maxDepth) {
      List.empty
    } else {
      val lis = secs.flatMap { s =>
        val aLink = a(href := s"#${s.id}")(s.name)
        li(aLink) :: renderTogglableTOC(s.children, maxDepth, depth + 1)
      }
      List(ul(lis))
    }

  private def renderScrollspyTocUl(
      secs: List[Section],
      maxDepth: Int = 2,
      depth: Int = 1
  ): List[Frag] =
    if (depth > maxDepth) {
      List.empty
    } else {
      val lis = secs.map { s =>
        val aLink = a(href := s"#${s.id}")(s.name)
        li(aLink :: renderScrollspyTocUl(s.children, maxDepth, depth + 1))
      }
      List(ul(cls := "nav nav-pills nav-stacked")(lis))
    }
}
