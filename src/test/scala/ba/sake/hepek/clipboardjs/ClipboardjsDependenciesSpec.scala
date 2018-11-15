package ba.sake.hepek.clipboardjs

import ba.sake.hepek.html.structure.{ComponentSettings, Dependency, DependencyProvider}
import ba.sake.hepek.matchers.HepekMatchers
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{FlatSpec, Matchers}

class ClipboardjsDependenciesSpec extends FlatSpec with Matchers with HepekMatchers {

  val clipboardJsDependencies = new ClipboardjsDependencies{}

  "ClipboardjsDependencies" should "contain the proper componentSettings" in {
    clipboardJsDependencies.clipboardjsSettings shouldBe a[ComponentSettings]
  }

  it should "have component settings named as clipboard.js" in {
    clipboardJsDependencies.clipboardjsSettings.pkg shouldBe "clipboard.js"
  }

  it should "have component major version greater or equal than 1 and a minor version greater or equal than 7" in {
    val semVer = clipboardJsDependencies.clipboardjsSettings.version.split('.')
    val (major, minor) = (semVer(0).toInt, semVer(1).toInt)
    (major, minor) should beEqualOrGreaterThanVersion(1, 7)
  }

  it should "have CDNjs as dependency provider" in {
    clipboardJsDependencies.clipboardjsSettings.depsProvider shouldBe DependencyProvider.cdnjs
  }

  it should "not have CSS dependencies" in {
    clipboardJsDependencies.clipboardjsDependencies.cssDependencies.deps shouldBe empty
  }

  it should "have a JS dependency" in {
      val jsDeps = clipboardJsDependencies.clipboardjsDependencies.jsDependencies.deps
      val jsDepVersion = clipboardJsDependencies.clipboardjsSettings.version
      val jsDepPackage = clipboardJsDependencies.clipboardjsSettings.pkg
      jsDeps.length shouldBe 1
      jsDeps.head shouldBe Dependency("clipboard.min.js", jsDepVersion, jsDepPackage)
  }

  it should "contain a list of components" in {
    clipboardJsDependencies.components.length shouldBe 1
    clipboardJsDependencies.components.head._1 shouldBe clipboardJsDependencies.clipboardjsSettings
    clipboardJsDependencies.components.head._2 shouldBe clipboardJsDependencies.clipboardjsDependencies
  }
}
