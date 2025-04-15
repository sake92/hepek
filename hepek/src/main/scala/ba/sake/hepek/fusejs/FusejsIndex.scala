package ba.sake.hepek.fusejs

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.path.ClassPackageRelativePath
import org.jsoup.Jsoup

trait FusejsIndex extends Renderable with ClassPackageRelativePath {
  def indexedPages: Seq[StaticPage]

  def fusejsIndexedPagesData: Seq[FusejsStaticPageData] = indexedPages.map { page =>
    val pageText = Jsoup.parse(page.render).text()
    FusejsStaticPageData(
      title = page.pageSettings.title,
      text = pageText,
      url = page.ref
    )
  }

  override def fileExtension: String = "json"

  override def render(): String = FusejsIndexBuilder.build(fusejsIndexedPagesData)
}
