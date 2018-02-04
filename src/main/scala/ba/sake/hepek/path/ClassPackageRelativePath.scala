package ba.sake.hepek.path

import ba.sake.hepek.utils.StringUtils

/**
  * A PackageRelativePath with `fileName` as the class simple name, plus the `fileExtension`. <br>
  * Default extension is "html". <br>
  * E.g. `class MyClass` will have `fileName` as "MyClass.html". <br>
  * Also, all '$' are removed, because `object`s have them... <br>
  * So the final path will be "my/package/my-class.html" (lowercased in PackageRelativePath)
  */
trait ClassPackageRelativePath extends PackageRelativePath {

  def fileExtension: String = "html"

  override def fileName: String = {
    val name = this.getClass.getSimpleName.replaceAll("\\$", "")
    StringUtils.urlify(name) + "." + fileExtension
  }

}
