package ba.sake.hepek.theme.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component.AllBootstrapComponents
import ba.sake.hepek.html.structure.blog.BlogPostPage
import ba.sake.hepek.html.structure.blog.Section

trait TocType

object TocType {
  object Togglable extends TocType
  case class Scrollspy(
      offset: Int = 10,
      affixOffset: Int = 10
  ) extends TocType
}

case class TocSettings(
    title: String = "Table of Contents",
    tocType: Option[TocType] = Some(TocType.Scrollspy())
)

trait HepekBootstrap3BlogPage extends BlogPostPage with BootstrapStaticPage {

  // avoid polluting user's namespace
  import AllBootstrapComponents._
  import HepekBootstrap3SectionUtils._

  def tocSettings: TocSettings = TocSettings()

  /**
    * @return Optional page header with page title.
    */
  def pageHeader: Option[Frag] = Some(
    div(cls := "page-header text-center hidden-print")(h1(pageSettings.title))
  )

  override def pageContent: Frag = {
    val maybeScrollspy = tocSettings.tocType.collect {
      case _: TocType.Scrollspy =>
        renderScrollspyTOC(blogSettings.sections)
    }

    frag(
      pageHeader.map(ph => row(ph)),
      row(
        div(cls := "col-lg-2 col-lg-offset-1  col-md-3  hidden-print")(renderSidebar),
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
          tag("article")(renderTocAndSections(blogSettings.sections)),
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
      nav#tocScrollspy .nav .nav>li>a {
          padding-top: 1px;
          padding-bottom: 1px;
          padding-left: 3em;
          font-size: .7em;
      }
    """
  )
  override def scriptsInline = {
    val maybeScrollSpy = tocSettings.tocType
      .collect {
        case TocType.Scrollspy(offset, affixOffset) =>
          List(s"""
            // activate scrollspy on body
            $$('body').scrollspy({
                target: '#tocScrollspy',
                offset: $offset // ~~ when the first heading starts...
            });
            
            // fix scrollspy navigation div
            $$('#tocScrollspy').affix({
                offset: $affixOffset // when to start moving fixed div
            })
          """)
      }
      .getOrElse(Nil)
    super.scriptsInline ++ maybeScrollSpy
  }

  /* CONTENT*/
  private def renderTocAndSections(secs: List[Section]): Frag = tocSettings.tocType match {
    case Some(TocType.Togglable) =>
      frag(
        togglableTOC(tocSettings.title, secs),
        div(renderSections(secs))
      )
    case _ => div(renderSections(secs))
  }

  // related pages
  private def renderSidebar: Frag = {
    val pageLiTags = for {
      p <- categoryPosts
      activeClass = if (p.relPath == relPath) "active" else ""
    } yield li(cls := activeClass, a(href := p.ref)(p.pageSettings.label))
    ul(cls := "nav nav-pills nav-stacked")(pageLiTags)
  }
}
