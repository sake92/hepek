package ba.sake.hepek.bulma.components

import ba.sake.hepek.bulma.{EmptyAttribute, Hoverable, Transparent}
import ba.sake.hepek.matchers.HepekMatchers
import org.scalatest.{FlatSpec, Matchers}

class BulmaSpec extends FlatSpec with Matchers with HepekMatchers {

  "enriched css class" should "end with an empty space" in {
    import ba.sake.hepek.bulma.component._
    enrichCssClass("test", Hoverable) shouldBe "test is-hoverable "
  }

  it should "end with the original css class enriched with empty space when enriched css class with EmptyAttribute" in {
    import ba.sake.hepek.bulma.component._
    enrichCssClass("test", EmptyAttribute) shouldBe "test "
  }

  it should "end with an empty space whenever enriched with multiple css classes" in {
    import ba.sake.hepek.bulma.component._
    enrichCssClasses("test", Hoverable, Transparent) shouldBe "test is-hoverable is-transparent "
    enrichCssClasses("test", Hoverable, EmptyAttribute, Transparent) shouldBe "test is-hoverable is-transparent "
    enrichCssClasses("test", Hoverable, EmptyAttribute) shouldBe "test is-hoverable "
  }

}
