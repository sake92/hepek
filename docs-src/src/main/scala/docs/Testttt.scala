package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure._
import utils._
import ba.sake.hepek.pure._, statik._
import ba.sake.hepek.pure.component._

object Testttt
    extends PureStaticPage
    with PureGridComponents
    with PureMenuComponents {

  override def siteSettings = SiteSettings(
    Site.name,
    docs.Index,
    List(docs.Docs, docs.Layout)
  )

  override def pageTitle       = "Welcome!"
  override def pageDescription = Option("Hepek docs")

  // override def menuType = PureMenuComponents.Type.Horizontal
  //override def withPureMenu = false

  override def pageContent =
    frag(
      row(
        third1("111111111"),
        third2(
          "2222222222"
        ),
        third3("333333333")
      )
    )

}
