package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.*

trait NavbarComponents {

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Seq[Frag] = Seq.empty,
      right: Seq[Frag] = Seq.empty
  ): Frag

  def link(url: String, content: Frag): Frag

  def dropdown(
      content: Frag,
      links: Seq[Frag] = Seq.empty
  ): Frag

  def dropdownLink(url: String, content: Frag): Frag =
    link(url, content)

}
