package ba.sake.hepek.path

import scala.jdk.CollectionConverters._

import ba.sake.hepek.core._

/** Scala wrapper for MultiRenderable
  */
trait ScalaMultiRenderable extends MultiRenderable {
  def rends: Seq[Renderable]

  override def renderables = rends.asJava
}
