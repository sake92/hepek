package ba.sake.hepek.selenium

import java.nio.file.Paths
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import ba.sake.hepek.core.Renderable

trait HepekSeleniumTest extends munit.FunSuite {
  given webDriver: WebDriver = new HtmlUnitDriver

  java.util.logging.Logger
    .getLogger("com.gargoylesoftware.htmlunit")
    .setLevel(java.util.logging.Level.OFF) // disable annoying HtmlUnit warnings

  val basePath = "hepek-tests/target/web/public/main/"

  def filePath(renderable: Renderable): String = {
    val pagePath = basePath + renderable.relPath
    val path     = Paths.get(pagePath)
    path.toUri.toString
  }

  // def getByCss(selector: String): Option[Element] =
  // find(cssSelector(selector))
}
