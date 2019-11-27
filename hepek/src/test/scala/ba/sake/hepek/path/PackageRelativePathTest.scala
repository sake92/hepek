package ba.sake.hepek.path

import java.nio.file.Paths

import org.scalatest.{FlatSpec, Matchers}

class PackageRelativePathTest extends FlatSpec with Matchers {
  "PackageRelativePath" should "make path based on package" in {
    val obj = PackageRelativeObject1
    obj.relPath shouldBe Paths.get("ba/sake/hepek/path/abc.txt")
  }
  it should "make path based on inner package" in {
    val obj = testme.PackageRelativeObject2
    obj.relPath shouldBe Paths.get("ba/sake/hepek/path/testme/index.html")
  }
}

object PackageRelativeObject1 extends PackageRelativePath {
  override def fileName = "abc.txt"
}

package object testme {

  object PackageRelativeObject2 extends PackageRelativePath {
    override def fileName = "index.html"
  }
}
