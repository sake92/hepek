package ba.sake.hepek.theme.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component.{
  AllBootstrapComponents,
  BootstrapNavbarComponent
}
import ba.sake.hepek.html.structure.blog.BlogPostPage
import ba.sake.hepek.html.structure.blog.Section

trait HepekBootstrap3BlogPage
    extends BlogPostPage
    with BootstrapStaticPage
    with BootstrapNavbarComponent {

  // avoid polluting user's namespace
  import AllBootstrapComponents._
  import HepekBootstrap3SectionUtils._

  def tocTitle: String = "Table of Contents"

  override def pageContent = frag(
    row(div(cls := "page-header text-center")(h1(pageTitle))),
    row(
      div(cls := "col-lg-2 col-lg-push-1  col-md-3")(sidebar),
      div(cls := "col-lg-8 col-lg-push-1  col-md-9")(
        postCreateDate.map(
          createDate =>
            div(span(cls := "glyphicon glyphicon-time"),
                " " + createDate.format(dateFormat))
        ),
        postAuthor.map(
          author => div(span(cls := "glyphicon glyphicon-user"), "  " + author)
        ),
        tag("article")(renderTOCAndSections(postSections)),
        div(id := "disqus_thread", cls := "hidden-print")
      )
    )
  )

  override def stylesInline = super.stylesInline ++ List(
    """
      article {
        padding-top: 12px;
      }
    """
  )

  /* CONTENT*/
  private def renderTOCAndSections(secs: List[Section], depth: Int = 1): Frag =
    frag(
      div(cls := "panel-group")(
        div(cls := "panel panel-default")(
          div(cls := "panel-heading")(
            h4(cls := "panel-title")(
              a(data.toggle := "collapse", href := "#collapseTOC")(tocTitle)
            )
          ),
          div(id := "collapseTOC", cls := "panel-collapse collapse")(
            div(cls := "panel-body pages-toc")(renderTOC(secs, depth))
          )
        )
      ),
      div(renderSections(secs, depth))
    )

  private def sidebar: Frag = {
    val pageLiTags = for {
      p <- categoryPosts
      activeClass = if (p.relPath == relPath) "active" else ""
    } yield li(cls := activeClass, a(href := relTo(p))(p.pageLabel))
    ul(cls := "nav nav-pills nav-stacked")(pageLiTags)
  }

}
