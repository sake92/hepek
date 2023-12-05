package ba.sake.hepek

import java.nio.file.Paths

import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.path.RelativePathAddons

final class Resource(fileName: String) extends RelativePath with RelativePathAddons {
  override def relPath = Paths.get(s"${Resource.siteRootPath}/${fileName}")
}

object Resource {
  // we pretend that the resource is in the "package files"
  // so that we get a nice relative link! #stonks
  private val siteRootPath = "files"
}
