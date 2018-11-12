package ba.sake.hepek.anchorjs
import ba.sake.hepek.html.structure.{ComponentSettings, Dependency, DependencyProvider}
import org.scalatest.{FlatSpec, Matchers}

class AnchorjsDependenciesSpec extends FlatSpec with Matchers {

  val anchorJsDependencies = new AnchorjsDependencies{}

  "AnchorjsDependencies" should "contain the proper componentSettings" in {
    anchorJsDependencies.anchorjsSettings shouldBe a[ComponentSettings]
  }

  it should "have component settings named as anchor-js" in {
    anchorJsDependencies.anchorjsSettings.pkg shouldBe "anchor-js"
  }

  it should "have component version greater than 4.0" in {
    anchorJsDependencies.anchorjsSettings.version.split('.')(0).toInt should be >= 4
  }

  it should "have CDNjs as dependency provider" in {
    anchorJsDependencies.anchorjsSettings.depsProvider shouldBe DependencyProvider.cdnjs
  }

  it should "not have CSS dependencies" in {
    anchorJsDependencies.anchorjsDependencies.cssDependencies.deps shouldBe empty
  }

  it should "have a JS dependency" in {
      val jsDeps = anchorJsDependencies.anchorjsDependencies.jsDependencies.deps
      val jsDepVersion = anchorJsDependencies.anchorjsSettings.version
      val jsDepPackage = anchorJsDependencies.anchorjsSettings.pkg
      jsDeps.length shouldBe 1
      jsDeps.head shouldBe Dependency("anchor.min.js", jsDepVersion, jsDepPackage)
  }

  it should "contain a list of components" in {
    anchorJsDependencies.components.length shouldBe 1
    anchorJsDependencies.components.head._1 shouldBe anchorJsDependencies.anchorjsSettings
    anchorJsDependencies.components.head._2 shouldBe anchorJsDependencies.anchorjsDependencies
  }
}
