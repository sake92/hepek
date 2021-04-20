package ba.sake.hepek.html.component

import ba.sake.hepek.scalatags.all, all.{caption => _, _}

trait ImageComponents {
  
  // https://stackoverflow.com/a/39563913/4496364
  def image(source: String, width: Int, height: Int, title: String = "", alt: String = ""): Frag

  def svg(source: String, title: String = ""): Frag
}
