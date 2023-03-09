package ba.sake.hepek.path

import ba.sake.hepek.core.RelativePath

/** RelativePath enrichments.
  */
trait RelativePathAddons extends RelativePath {

  /** Used as context when referring to other Renderables, when creating blog sections etc. */
  given selfRef: RelativePath = this

  def ref(using caller: RelativePath) =
    caller.relTo(this)
}
