package ba.sake.hepek.path

import java.nio.file.Paths
import org.scalatest.{FlatSpec, Matchers}

class ClassPackageRelativePathTest extends FlatSpec with Matchers {
  "ClassPackageRelativePath" should "make path based on class and package" in {
    val obj = ClassPackageObject1
    obj.relPath shouldBe Paths.get("ba/sake/hepek/path/class-package-object1.html")
  }
  it should "respect custom extension" in {
    val obj = CustomExtensionObject1
    obj.relPath shouldBe Paths.get("ba/sake/hepek/path/custom-extension-object1.xml")
  }
}

object ClassPackageObject1 extends ClassPackageRelativePath

object CustomExtensionObject1 extends ClassPackageRelativePath {
  override def fileExtension = "xml"
}
