package ba.sake.hepek.path

import ba.sake.hepek.core.RelativePath

/**
  * RelativePath enrichments. <br>
  */
trait RelativePathAddons extends RelativePath {

  /** Used as context when referring to other Renderables, when creating blog sections etc. */
  implicit def selfRef: RelativePath = this

  def ref(implicit caller: RelativePath) =
    caller.relTo(this)

}
