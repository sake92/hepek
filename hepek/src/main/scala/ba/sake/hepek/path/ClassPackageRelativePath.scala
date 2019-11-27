package ba.sake.hepek.path

import ba.sake.hepek.utils.StringUtils

/**
  * A PackageRelativePath with `fileName` as the class simple name, plus the `fileExtension`
  * (default is "html"). <br>
  * E.g. `class MyClass` will have `fileName` as "MyClass.html". <br>
  * Also, all '$' are removed, because `object`s have them... <br>
  * So the final path will be "my/package/my-class.html" ("urlify"-ed also, prefixed with PackageRelativePath)
  */
trait ClassPackageRelativePath extends PackageRelativePath {
  def fileExtension: String = "html"

  override def fileName: String = {
    val name = this.getClass.getSimpleName.replaceAll("\\$", "")
    StringUtils.urlify(name) + "." + fileExtension
  }
}
