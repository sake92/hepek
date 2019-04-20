package ba.sake.hepek.bulma.component

import ba.sake.hepek.bulma._
import scalatags.Text.all._

object CardComponents extends CardComponents

trait CardHeader extends BulmaElement {

  def contentBuilder(
      attributeClass: BulmaModifier*
  )(title: String, iconClass: Option[String] = None) =
    header(cls := "card-header")(
      p(cls := enrichCssClasses("card-header-title", attributeClass), title),
      iconClass.fold[Frag](SeqFrag[String](List()))(
        className =>
          a(href := "#", cls := "card-header-icon")(span(cls := "icon", i(cls := className)))
      )
    )
}

case class CenteredHeader(title: String, iconClass: Option[String] = None) extends CardHeader {
  override def content = contentBuilder(Centered)(title, iconClass)
}

object DefaultHeader {
  def apply(title: String) = new DefaultHeader(title, None)
}
case class DefaultHeader(title: String, iconClass: Option[String]) extends CardHeader {
  override def content = contentBuilder(EmptyAttribute)(title, iconClass)
}

case class CardImage(imagePath: String) extends BulmaElement {
  override def content =
    div(cls := "card-image")(
      tag("figure")(cls := "image is-4by3")(img(src := imagePath))
    )
}

case class CardContent(fragments: Frag*) extends BulmaElement {
  override def content = div(cls := "card-content")(fragments)
}

case class CardFooter(items: Frag*) extends BulmaElement {
  override def content =
    footer(cls := "card-footer")(
      for {
        item <- items
      } yield a(cls := "card-footer-item")(item)
    )
}

trait CardComponents {

  def card(
      header: Option[CardHeader] = None,
      image: Option[CardImage] = None,
      cardContent: CardContent,
      footer: Option[CardFooter] = None
  ) =
    div(cls := "card")(
      optionalElementContent(header),
      optionalElementContent(image),
      cardContent.content,
      optionalElementContent(footer)
    )

}
