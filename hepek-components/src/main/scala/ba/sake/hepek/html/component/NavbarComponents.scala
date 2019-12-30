package ba.sake.hepek.html.component

import scalatags.Text.all._

trait NavbarComponents {

  def navbar(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[(Frag, Seq[AttrPair])] = Seq.empty,
      right: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag

  def navbarNestedLink(
      title: Frag,
      links: Seq[(Frag, Seq[AttrPair])] = Seq.empty
  ): Frag
}
