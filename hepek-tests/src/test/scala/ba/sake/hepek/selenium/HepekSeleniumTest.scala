package ba.sake.hepek.selenium

import java.nio.file.Paths
import ba.sake.hepek.core.Renderable
import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatestplus.selenium.HtmlUnit
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait HepekSeleniumTest extends AnyFlatSpec with Matchers with HtmlUnit with Eventually {
  java.util.logging.Logger
    .getLogger("com.gargoylesoftware.htmlunit")
    .setLevel(java.util.logging.Level.OFF) // disable annoying HtmlUnit warnings

  val basePath = "hepek-tests/target/web/public/main/"

  def filePath(renderable: Renderable): String = {
    val pagePath = basePath + renderable.relPath
    val path     = Paths.get(pagePath)
    path.toUri.toString
  }
}
