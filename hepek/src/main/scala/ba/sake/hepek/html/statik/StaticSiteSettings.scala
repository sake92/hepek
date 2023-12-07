package ba.sake.hepek.html.statik

final class StaticSiteSettings private (
    val indexPage: Option[StaticPage],
    val mainPages: List[StaticPage]
) {

  def withIndexPage(indexPage: Option[StaticPage]): StaticSiteSettings =
    copy(indexPage = indexPage)

  def withIndexPage(indexPage: StaticPage): StaticSiteSettings =
    withIndexPage(Option(indexPage))

  def withMainPages(mainPages: List[StaticPage]): StaticSiteSettings =
    copy(mainPages = mainPages)

  def withMainPages(mainPages: StaticPage*): StaticSiteSettings =
    withMainPages(mainPages.toList)

  private def copy(
      indexPage: Option[StaticPage] = indexPage,
      mainPages: List[StaticPage] = mainPages
  ) = new StaticSiteSettings(indexPage, mainPages)

}

object StaticSiteSettings:
  val default: StaticSiteSettings = new StaticSiteSettings(None, List.empty)
