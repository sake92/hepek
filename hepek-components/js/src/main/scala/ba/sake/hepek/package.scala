package ba.sake

import org.scalajs.dom
import scalatags.JsDom

package object hepek {
  val scalatags = JsDom

  // funky implicits errors..
  implicit def string2Node(v: String): dom.Node =
    new JsDom.StringFrag(v).render
}
