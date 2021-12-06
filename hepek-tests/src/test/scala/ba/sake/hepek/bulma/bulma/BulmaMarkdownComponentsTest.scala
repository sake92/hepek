package ba.sake.hepek.bulma.component

import ba.sake.hepek.selenium.HepekSeleniumTest
import fixtures.static._
import org.openqa.selenium.By

class BulmaMarkdownComponentsTest extends HepekSeleniumTest {
  "BulmaMarkdownComponents" should "render markdown properly" in {
    /* go to filePath(Bulma)

    val mdDiv = find(ids.md).get
    for (i <- 1 to 6) {
      val header = mdDiv.underlying.findElement(By.tagName(s"h$i"))
      header.getAttribute("class") should include(s"is-$i")
      header.getAttribute("class") should include("title")
    }*/
  }
}
