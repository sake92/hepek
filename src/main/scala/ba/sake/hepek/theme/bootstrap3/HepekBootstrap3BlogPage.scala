package ba.sake.hepek.theme.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component.AllBootstrapComponents
import ba.sake.hepek.html.structure.blog.BlogPostPage
import ba.sake.hepek.html.structure.blog.Section

trait TocType

object TocType {
  object Togglable extends TocType
  object Scrollspy extends TocType
}

trait HepekBootstrap3BlogPage extends BlogPostPage with BootstrapStaticPage {

  // avoid polluting user's namespace
  import AllBootstrapComponents._
  import HepekBootstrap3SectionUtils._

  def tocTitle: String         = "Table of Contents"
  def tocType: Option[TocType] = Some(TocType.Scrollspy)

  /**
    * @return Optional page header with page title.
    */
  def pageHeader: Option[Frag] = Some(
    div(cls := "page-header text-center hidden-print")(h1(pageSettings.title))
  )

  override def pageContent: Frag = {
    val maybeScrollspy = tocType.collect {
      case TocType.Scrollspy =>
        renderScrollspyTOC(blogSettings.sections)
    }

    frag(
      pageHeader.map(ph => row(ph)),
      row(
        div(cls := "col-lg-2 col-lg-offset-1  col-md-3  hidden-print")(sidebar),
        div(cls := "col-lg-6                  col-md-6")(
          div(cls := "hidden-print")(
            blogSettings.createDate.map(
              cd =>
                div(span(cls := "glyphicon glyphicon-time"),
                    " " + cd.format(blogSettings.dateFormat))
            ),
            blogSettings.author.map(
              author => div(span(cls := "glyphicon glyphicon-user"), "  " + author)
            )
          ),
          tag("article")(renderTOCAndSections(blogSettings.sections)),
          div(id := "disqus_thread", cls := "hidden-print")
        ),
        div(cls := "col-lg-3                  col-md-3  hidden-print")(maybeScrollspy)
      )
    )
  }

  override def stylesInline = super.stylesInline ++ List(
    """
      article {
        padding-top: 12px;
      }
    """
  )
  override def scriptsInline = {
    val maybeScrollSpy = tocType
      .collect {
        case TocType.Scrollspy =>
          List("""
            $('body').scrollspy({
                target: '#tocScrollspy',
                offset: 40
            });
          """)
      }
      .getOrElse(Nil)
    super.scriptsInline ++ maybeScrollSpy
  }

  /* CONTENT*/
  private def renderTOCAndSections(secs: List[Section], depth: Int = 1): Frag = tocType match {
    case Some(TocType.Togglable) =>
      frag(
        togglableTOC(secs, depth),
        div(renderSections(secs, depth))
      )
    case _ => div(renderSections(secs, depth))
  }

  // TOC
  private def togglableTOC(secs: List[Section], depth: Int = 1): Frag =
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

  // related pages
  private def sidebar: Frag = {
    val pageLiTags = for {
      p <- categoryPosts
      activeClass = if (p.relPath == relPath) "active" else ""
    } yield li(cls := activeClass, a(href := p.ref)(p.pageSettings.label))
    ul(cls := "nav nav-pills nav-stacked")(pageLiTags)
  }
}
