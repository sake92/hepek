package ba.sake.hepek.theme.bootstrap5

import ba.sake.hepek.html.statik.BlogPostPage
import HepekBootstrap5Utils.*
import Bundle.*, Tags.*, Classes.*

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

// NOTE: scrollspy will NOT WORK if the page is NOT SCROLLABE (not enough content..
trait HepekBootstrap5BlogPage extends BlogPostPage with StaticPage {

  // optional TOC
  def tocSettings: Option[TocSettings] = Some(TocSettings())

  // optional page header with page title
  def pageHeader: Option[Frag] = Some(
    div(cls := "page-header", Classes.txtAlignCenter, Classes.clsNoPrint)(
      h1(pageSettings.title)
    )
  )

  override def pageContent: Frag = {
    val maybeScrollspy = tocSettings.map(_.tocType).collect { case _: TocType.Scrollspy =>
      renderScrollspyTOC(blogSettings.sections)
    }

    val (w1, w2, w3) =
      if (categoryPosts.nonEmpty && tocSettings.nonEmpty) (3, 6, 3)
      else if (categoryPosts.isEmpty) (1, 8, 3)
      else (3, 8, 1)

    frag(
      div(clsContainerFluid)(
        div(cls := "row")(
          div(cls := s"col-md-$w1 d-flex justify-content-end", Classes.clsNoPrint)(
            renderSidebar
          ),
          div(data.bs.spy := "scroll", data.bs.target := "#tocScrollspy", cls := s"col-md-$w2")(
            div(Classes.clsNoPrint)(
              blogSettings.createdDate.map(cd =>
                div(
                  span(cls := "glyphicon glyphicon-time"),
                  " " + cd.format(blogSettings.dateFormat)
                )
              ),
              blogSettings.author.map(author =>
                div(span(cls := "glyphicon glyphicon-user"), "  " + author)
              )
            ),
            pageHeader,
            renderTocAndSections(blogSettings.sections)
          ),
          div(cls := s"col-md-$w3 hidden-sm hidden-xs", Classes.clsNoPrint)(
            maybeScrollspy
          )
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
          font-size: .8em;
      }
    """
  )

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
    } yield li(a(href := p.ref, cls := "nav-link", cls := activeClass)(p.pageSettings.label))
    tag("nav")(cls := "affix")(
      ul(cls := "nav nav-pills flex-column")(pageLiTags)
    )
  }
}
