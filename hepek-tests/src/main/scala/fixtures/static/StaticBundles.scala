package fixtures.static

import scalatags.Text.all._
import ba.sake.hepek.bulma.statik.BulmaStaticPage
import ba.sake.hepek.bulma.statik.BulmaStaticBundle
import ba.sake.hepek.html.StaticBundle

import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.html.component.MarkdownComponents
import ba.sake.hepek.html.statik.StaticPage
import ba.sake.hepek.plain.statik.PlainStaticBundle
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.w3css.statik.W3CssStaticBundle

object StaticPages {
  val all = List(Plain, Bootstrap3, Bulma, W3Css)
}

object Plain extends SimpleStaticPage {
  val bundle = PlainStaticBundle
}

object Bootstrap3 extends SimpleStaticPage {
  val bundle = BootstrapStaticBundle
}

object Bulma extends SimpleStaticPage {
  val bundle = BulmaStaticBundle
}

object W3Css extends SimpleStaticPage {
  val bundle = W3CssStaticBundle
}

object ids {
  val md = "md1"
}

trait SimpleStaticPage extends StaticPage {
  val bundle: StaticBundle with BasicComponents
  import bundle._

  override def siteSettings =
    super.siteSettings
      .withName("Site name")
      .withFaviconNormal("site-favicon.png")

  override def pageCategory =
    Some("Page category")

  override def pageSettings =
    super.pageSettings
      .withTitle("Page title")
      .withLabel("Page link label")
      .withDescription("Page description")
      .withLanguage("bs")

  override def pageContent =
    frag(
      div(id := ids.md)(
        """
          # Header1
          ## Header2
          ### Header3
          #### Header4
          ##### Header5
          ###### Header6
        """.md
      )
    )
}
