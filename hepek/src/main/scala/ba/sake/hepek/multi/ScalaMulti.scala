package ba.sake.hepek.path

import scala.jdk.CollectionConverters.*

import ba.sake.hepek.core.*

/** Scala wrapper for MultiRenderable
  */
trait ScalaMultiRenderable extends MultiRenderable {
  def rends: Seq[Renderable]

  override def renderables = rends.asJava
}
