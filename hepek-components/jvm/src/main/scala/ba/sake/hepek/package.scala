package ba.sake

import scalatags.Text
import java.net.URLConnection

package object hepek {
  val scalatags = Text

  def guessMimeType(filename: String): String =
    URLConnection.guessContentTypeFromName(filename)
}
