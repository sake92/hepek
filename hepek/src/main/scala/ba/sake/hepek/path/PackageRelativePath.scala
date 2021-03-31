package ba.sake.hepek.path

import java.nio.file.Paths
import ba.sake.hepek.core.RelativePath
import java.nio.file.Path

/**
  * Path relative to the class' package. <br>
  * E.g. `object MyPage extends PackageRelativePath { def fileName = "mypage.html" }`
  * in `my.company` package renders to `my/company/mypage.html`
  */
trait PackageRelativePath extends RelativePath with RelativePathAddons {
  /** Name of the rendered file. */
  def fileName: String

  override def relPath: Path = {
    val path = if (this.getClass.getPackage == null) {
      fileName
    } else {
      this.getClass.getPackage.getName.replaceAll("\\.", "/") + "/" + fileName
    }
    Paths.get(path)
  }
}
