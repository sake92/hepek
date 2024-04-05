package ba.sake.hepek.alpinejs

import ba.sake.hepek.scalatags.all.*

object x {
  val data = attr("x-data")

  val init = attr("x-init")

  val show = attr("x-show")

  def bind(property: String) = attr(s"x-bind:${property}")

  def on(event: String) = attr(s"x-on:${event}")

  val text = attr("x-text")

  val html = attr("x-html")

  val model = attr("x-model")

  val modelable = attr("x-modelable")

  val forr  = attr("x-for")
  val `for` = attr("x-for")

  def transition(customization: String) = attr(s"x-transition${customization}")

  val effect = attr("x-effect")

  val ignore = attr("x-ignore").empty

  val ref = attr("x-ref")

  val cloak = attr("x-cloak").empty

  val teleport = attr("x-teleport")

  val iff  = attr("x-if")
  val `if` = attr("x-if")

  val id = attr("x-id")
}
