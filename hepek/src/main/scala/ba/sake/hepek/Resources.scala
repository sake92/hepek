package ba.sake.hepek

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.path.RelativePathAddons
import java.nio.file.Paths
import java.nio.file.Path

final case class Resource(fileName: String) extends RelativePath with RelativePathAddons {
  override def relPath: Path = Paths.get(fileName)
}

object Resources extends Resources

trait Resources {
  def siteRootPath: String = "site"

  def resource(fileName: String): Resource =
    Resource(s"$siteRootPath/$fileName")

  // css folder
  object styles {
    def css(baseName: String): Resource = resource(s"styles/$baseName.css")
  }

  // js folder
  object scripts {
    def js(baseName: String): Resource = resource(s"scripts/$baseName.js")
  }

  // images folder
  object images {
    def image(fullName: String): Resource = resource(s"images/$fullName")
    def ico(baseName: String): Resource   = image(baseName + ".ico")
    def svg(baseName: String): Resource   = image(baseName + ".svg")
    def jpg(baseName: String): Resource   = image(baseName + ".jpg")
    def jpeg(baseName: String): Resource  = image(baseName + ".jpeg")
    def png(baseName: String): Resource   = image(baseName + ".png")
    def gif(baseName: String): Resource   = image(baseName + ".gif")
  }

  // lib folder
  object lib {
    def js(baseName: String): Resource    = resource(s"lib/$baseName.js")
    def jsMin(baseName: String): Resource = js(baseName + ".min")

    def css(baseName: String): Resource    = resource(s"lib/$baseName.css")
    def cssMin(baseName: String): Resource = css(baseName + ".min")
  }
}
