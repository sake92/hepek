package ba.sake.hepek.html.component

import scalatags.Text.all._

trait NavbarComponents {

  def full(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag

  def simple(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[Frag] = Seq.empty,
      right: Seq[Frag] = Seq.empty
  ): Frag =
    full(brandUrl, brandName, brandIconUrl, left.map(_ -> Seq.empty), right.map(_ -> Seq.empty))

  def fullNestedLink(
      title: Frag,
      links: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag

  def simpleNestedLink(
      title: Frag,
      links: Seq[Frag] = Seq.empty
  ): Frag =
    fullNestedLink(title, links.map(_ -> Seq.empty))
}
