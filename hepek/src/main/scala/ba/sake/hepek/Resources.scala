package ba.sake.hepek

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.path.RelativePathAddons
import java.nio.file.Paths

final case class Resource(fileName: String) extends RelativePath with RelativePathAddons {
  override def relPath = Paths.get(fileName)
}

object Resources extends Resources

trait Resources {
  def siteRootPath: String = "site"

  def resource(fileName: String): Resource =
    Resource(s"$siteRootPath/$fileName")

  // css folder
  object styles {
    def css(baseName: String) = resource(s"styles/$baseName.css")
  }

  // js folder
  object scripts {
    def js(baseName: String) = resource(s"scripts/$baseName.js")
  }

  // images folder
  object images {
    def image(fullName: String) = resource(s"images/$fullName")
    def ico(baseName: String)   = image(baseName + ".ico")
    def svg(baseName: String)   = image(baseName + ".svg")
    def jpg(baseName: String)   = image(baseName + ".jpg")
    def jpeg(baseName: String)  = image(baseName + ".jpeg")
    def png(baseName: String)   = image(baseName + ".png")
    def gif(baseName: String)   = image(baseName + ".gif")
  }

  // lib folder
  object lib {
    def js(baseName: String)    = resource(s"lib/$baseName.js")
    def jsMin(baseName: String) = js(baseName + ".min")

    def css(baseName: String)    = resource(s"lib/$baseName.css")
    def cssMin(baseName: String) = css(baseName + ".min")
  }
}
