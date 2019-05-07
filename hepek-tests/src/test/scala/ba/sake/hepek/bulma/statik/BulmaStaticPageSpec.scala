package ba.sake.hepek.bulma.statik

import java.util

import ba.sake.hepek.selenium.HepekSeleniumTest
import org.openqa.selenium.{By, WebElement}

class BulmaStaticPageSpec extends HepekSeleniumTest {
  "Bulma Static Page" should "contain Bulma dependencies" in {
    val p = MockBulmaPage
    go to filePath(p)
    val bulmaDependency = find(cssSelector("link[rel='stylesheet']"))
    bulmaDependency.get.attribute("href").get should endWith("bulma.min.css")
  }

  "Bulma Static Page container" should "contain a navbar and a card" in {
    val p = MockBulmaPage
    go to filePath(p)

    val navbar = find(className("navbar"))
    val items  = navbar.get.underlying.findElements(By.tagName("a"))
    items.size shouldBe 3
    items.get(0).getText shouldBe "test-1"

    val cardContent =
      find(className("card-content")).get.underlying.findElement(By.tagName("p")).getText
    cardContent shouldBe "hello"
  }

}
