package ba.sake.hepek.jquery

import ba.sake.hepek.html.structure.{ComponentSettings, Dependency, DependencyProvider}
import ba.sake.hepek.matchers.HepekMatchers
import org.scalatest.{FlatSpec, Matchers}

class JQueryDependenciesSpec extends FlatSpec with Matchers with HepekMatchers {

  val jqueryDependencies = new JQueryDependencies {}

  "JQueryDependencies" should "contain the proper componentSettings" in {
    jqueryDependencies.jQuerySettings shouldBe a[ComponentSettings]
  }

  it should "have component settings named as jquery" in {
    jqueryDependencies.jQuerySettings.pkg shouldBe "jquery"
  }

  it should "have component major version greater or equal than 3 and a minor version greater or equal than 2" in {
    val semVer         = jqueryDependencies.jQuerySettings.version.split('.')
    val (major, minor) = (semVer(0).toInt, semVer(1).toInt)
    (major, minor) should beEqualOrGreaterThanVersion(3, 2)
  }

  it should "have CDNjs as dependency provider" in {
    jqueryDependencies.jQuerySettings.depsProvider shouldBe DependencyProvider.cdnjs
  }

  it should "not have CSS dependencies" in {
    jqueryDependencies.jQueryDependencies.cssDependencies.deps shouldBe empty
  }

  it should "have a JS dependency" in {
    val jsDeps       = jqueryDependencies.jQueryDependencies.jsDependencies.deps
    val jsDepVersion = jqueryDependencies.jQuerySettings.version
    val jsDepPackage = jqueryDependencies.jQuerySettings.pkg
    jsDeps.length shouldBe 1
    jsDeps.head shouldBe Dependency("jquery.min.js", jsDepVersion, jsDepPackage, Option("dist/"))
  }

  it should "contain a list of components" in {
    jqueryDependencies.components.length shouldBe 1
    jqueryDependencies.components.head._1 shouldBe jqueryDependencies.jQuerySettings
    jqueryDependencies.components.head._2 shouldBe jqueryDependencies.jQueryDependencies
  }
}
