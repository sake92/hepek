package ba.sake.hepek.path

import java.nio.file.Paths

class PackageRelativePathTest extends munit.FunSuite {
  test("make path based on package") {
    val obj = PackageRelativeObject1
    assertEquals(obj.relPath, Paths.get("ba/sake/hepek/path/abc.txt"))
  }
  test("make path based on inner package") {
    val obj = testme.PackageRelativeObject2
    assertEquals(obj.relPath, Paths.get("ba/sake/hepek/path/testme/index.html"))
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
