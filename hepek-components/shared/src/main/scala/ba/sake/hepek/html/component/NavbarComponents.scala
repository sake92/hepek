package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all.*

trait NavbarComponents {

  def nav(
      brandUrl: String,
      brandName: Option[String] = None,
      brandIconUrl: Option[String] = None,
      left: Frag = frag(),
      right: Frag = frag()
  ): Frag

  def link(url: String, content: Frag): Frag

  def dropdown(
      content: Frag,
      links: Seq[Frag] = Seq.empty
  ): Frag

  def dropdownLink(url: String, content: Frag): Frag =
    link(url, content)

}
