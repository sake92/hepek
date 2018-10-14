package ba.sake.hepek.theme.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section

object HepekBootstrap3SectionUtils extends HepekBootstrap3SectionUtils

trait HepekBootstrap3SectionUtils {

  /** Renders the sections. */
  def renderSections(secs: List[Section], depth: Int): List[Frag] =
    secs.flatMap { s =>
      val hTag = tag("h" + (depth + 1)) // depth = h2, h3...
      val thisSection =
        tag("section")(hTag(id := s.id)(s.name), s.content)
      thisSection :: renderSections(s.children, depth + 1)
    }

  /** Renders the togglable TOC (Table of Contents). */
  def togglableTOC(tocTitle: String, secs: List[Section], depth: Int = 1): Frag =
    div(cls := "panel-group hidden-print")(
      div(cls := "panel panel-default")(
        div(cls := "panel-heading")(
          h4(cls := "panel-title")(
            a(data.toggle := "collapse", href := "#collapseTOC")(tocTitle)
          )
        ),
        div(id := "collapseTOC", cls := "panel-collapse collapse")(
          div(cls := "panel-body pages-toc")(renderTogglableTOC(secs, depth))
        )
      )
    )

  /** Renders the scrollable TOC (Table of Contents). */
  def renderScrollspyTOC(secs: List[Section], depth: Int = 1): Frag =
    tag("nav")(id := "tocScrollspy")(
      renderScrollspyTocUl(secs, depth)
    )

  /* helpers */
  private def renderTogglableTOC(secs: List[Section], depth: Int = 1): List[Frag] = {
    val lis = secs.flatMap { s =>
      val aLink = a(href := s"#${s.id}")(s.name)
      li(aLink) :: renderTogglableTOC(s.children, depth + 1)
    }
    List(ul(lis))
  }

  private def renderScrollspyTocUl(secs: List[Section], depth: Int = 1): List[Frag] = {
    val lis = secs.map { s =>
      val aLink = a(href := s"#${s.id}")(s.name)
      li(aLink :: renderScrollspyTocUl(s.children, depth + 1))
    }
    List(ul(cls := "nav nav-pills nav-stacked")(lis))
  }
}
