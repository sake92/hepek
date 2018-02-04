package ba.sake.hepek

import ba.sake.hepek.core.RelativePath
import java.nio.file.Paths

case class Resource(fileName: String) extends RelativePath {

  override def relPath = Paths.get("site/" + fileName)

}

object Resources {

  // css folder
  object styles {
    def css(baseName: String) = Resource(s"styles/$baseName.css")
  }

  // js folder
  object scripts {
    def js(baseName: String) = Resource(s"scripts/$baseName.js")
  }

  // images folder
  object images {
    def image(fullName: String) = Resource(s"images/$fullName")
    def ico(baseName: String)   = image(baseName + ".ico")
    def svg(baseName: String)   = image(baseName + ".svg")
    def jpg(baseName: String)   = image(baseName + ".jpg")
    def gif(baseName: String)   = image(baseName + ".gif")
  }

  // lib folder
  object lib {
    def js(baseName: String)    = Resource(s"lib/$baseName.js")
    def jsMin(baseName: String) = js(baseName + ".min")

    def css(baseName: String)    = Resource(s"lib/$baseName.css")
    def cssMin(baseName: String) = css(baseName + ".min")
  }

}
