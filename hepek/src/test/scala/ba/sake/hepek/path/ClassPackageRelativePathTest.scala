package ba.sake.hepek.path

import java.nio.file.Paths

class ClassPackageRelativePathTest extends munit.FunSuite {
  test("make path based on class and package") {
    val obj = ClassPackageObject1
    assertEquals(obj.relPath, Paths.get("ba/sake/hepek/path/class-package-object1.html"))
  }
  test("respect custom extension") {
    val obj = CustomExtensionObject1
    assertEquals(obj.relPath, Paths.get("ba/sake/hepek/path/custom-extension-object1.xml"))
  }
}

object ClassPackageObject1 extends ClassPackageRelativePath

object CustomExtensionObject1 extends ClassPackageRelativePath {
  override def fileExtension = "xml"
}
