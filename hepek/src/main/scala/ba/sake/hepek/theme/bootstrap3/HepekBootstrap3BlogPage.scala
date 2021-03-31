package ba.sake.hepek.theme.bootstrap3

import ba.sake.hepek.bootstrap3.component.classes.BootstrapClassesBundle._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.html.statik.BlogPostPage
import ba.sake.hepek.html.statik.Section
import ba.sake.hepek.scalatags.all._

trait TocType

object TocType {
  object Togglable extends TocType

  final case class Scrollspy(
      offset: Int = 10,
      affixOffset: Int = 10
  ) extends TocType
}

final case class TocSettings(
    title: String = "Table of Contents",
    tocType: TocType = TocType.Scrollspy()
)

object HepekBootstrap3BlogPage {
  val Bundle = BootstrapStaticBundle()
}
import HepekBootstrap3BlogPage.Bundle._

// NOTE: scrollspy will NOT WORK if the page is NOT SCROLLABE (not enough content..)
trait HepekBootstrap3BlogPage extends BlogPostPage with StaticPage {
  import HepekBootstrap3SectionUtils._

  // optional TOC
  def tocSettings: Option[TocSettings] = Some(TocSettings())

  // optional page header with page title
  def pageHeader: Option[Frag] = Some(
    div(cls := "page-header", txtAlignCenter, clsNoPrint)(
      h1(pageSettings.title)
    )
  )

  override def pageContent: Frag = {
    val maybeScrollspy = tocSettings.map(_.tocType).collect {
      case _: TocType.Scrollspy =>
        renderScrollspyTOC(blogSettings.sections)
    }

    val (w1, w2, w3) =
      if (categoryPosts.nonEmpty && tocSettings.nonEmpty) (3, 6, 3)
      else if (categoryPosts.isEmpty) (1, 8, 3)
      else (3, 8, 1)

    frag(
      pageHeader,
      div(cls := "row")(
        div(cls := s"col-md-$w1", clsNoPrint)(
          renderSidebar
        ),
        div(cls := s"col-md-$w2")(
          div(clsNoPrint)(
            blogSettings.createdDate.map(
              cd =>
                div(
                  span(cls := "glyphicon glyphicon-time"),
                  " " + cd.format(blogSettings.dateFormat)
                )
            ),
            blogSettings.author.map(
              author => div(span(cls := "glyphicon glyphicon-user"), "  " + author)
            )
          ),
          renderTocAndSections(blogSettings.sections),
          div(id := "disqus_thread", clsNoPrint)
        ),
        div(cls := s"col-md-$w3 hidden-sm hidden-xs", clsNoPrint)(
          maybeScrollspy
        )
      )
    )
  }

  override def stylesInline = super.stylesInline ++ List(
    """
      /* scrollspy stuff */
      nav#tocScrollspy  .nav>li>a {
        padding-top: 1px;
        padding-bottom: 1px;
        font-size: .9em;
      }
      nav#tocScrollspy .nav .nav>li>a {
          padding-top: 1px;
          padding-bottom: 1px;
          padding-left: 3em;
          font-size: .7em;
      }
      
      /* turn off affix on screens less than md */
      @media (max-width: 991px) { 
          .affix { position: static; }
      }
      @media (min-width: 992px) {
          /* col-3 is 25% but it's nicer like this */
          .affix { width: 23%; }
      }
      @media (min-width: 992px) {
          .affix {
              overflow-y: auto;
              height: 85%; /* nicer if not full height */
          }
      }
      .affix { padding-left: 2em; }
    """
  )

  override def scriptsInline = {
    val maybeScrollSpy = tocSettings
      .map(_.tocType)
      .collect {
        case TocType.Scrollspy(offset, affixOffset) =>
          List(s"""
            // activate scrollspy on body
            $$('body').scrollspy({
                target: '#tocScrollspy',
                offset: $offset // ~~ when the first heading starts...
            });
            
            // fix scrollspy for current page sections
            $$('#tocScrollspy').affix({
                offset: { top: $affixOffset } // when to start moving fixed div
            });
          """)
      }
      .getOrElse(Nil)
    super.scriptsInline ++ maybeScrollSpy
  }

  /* CONTENT*/
  private def renderTocAndSections(secs: List[Section]): Frag = tocSettings.map { ts =>
    if (ts.tocType == TocType.Togglable)
      frag(
        togglableTOC(ts.title, secs),
        div(renderSections(secs))
      )
    else div(renderSections(secs))
  }

  // related pages
  private def renderSidebar: Frag = {
    val pageLiTags = for {
      p <- categoryPosts
      activeClass = if (p.relPath == relPath) "active" else ""
    } yield li(cls := activeClass, a(href := p.ref)(p.pageSettings.label))
    tag("nav")(cls := "affix")(
      ul(cls := "nav nav-pills nav-stacked")(pageLiTags)
    )
  }
}
