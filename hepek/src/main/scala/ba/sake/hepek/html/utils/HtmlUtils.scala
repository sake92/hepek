package ba.sake.hepek.html

import org.jsoup.Jsoup
import org.jsoup.nodes._

object HtmlUtils {

  def process(htmlText: String, pretty: Boolean, xhtml: Boolean): String =
    if (xhtml || pretty) {
      val document = Jsoup.parse(htmlText)
      document
        .outputSettings()
        .prettyPrint(pretty)
        .outline(true)
      if (xhtml) {
        document
          .outputSettings()
          .escapeMode(Entities.EscapeMode.xhtml)
          .syntax(Document.OutputSettings.Syntax.xml)
      }
      document.html
    } else { // if nothing to do...
      htmlText
    }
}
