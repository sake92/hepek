package ba.sake.hepek.path

import scala.collection.JavaConverters._
import ba.sake.hepek.core._

/** Scala wrapper for MultiRenderable
  */
trait ScalaMultiRenderable extends MultiRenderable {
  def rends: Seq[Renderable]

  def renderables = rends.asJava
}
