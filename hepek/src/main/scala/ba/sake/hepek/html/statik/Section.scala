package ba.sake.hepek.html.statik

import ba.sake.hepek.scalatags.*
import ba.sake.hepek.core.RelativePath
import ba.sake.hepek.utils.StringUtils

/** Handy class for sectioning a page (usually blog post). <br> Also useful for generating a TOC
  * (Table Of Contents).
  */
final class Section private (
    val name: String,
    val content: Frag,
    val children: List[Section]
)(using owner: RelativePath) {

  def withName(name: String): Section           = copy(name = name)
  def withContent(content: Frag): Section       = copy(content = content)
  def withChildren(children: Section*): Section = copy(children = children.toList)

  def id: String = StringUtils.urlify(name)

  /** Refer to section only by its path, e.g. Index.mySection.ref */
  def ref(using caller: RelativePath): String =
    if (owner == caller) s"#${id}"
    else caller.relTo(owner) + s"#${id}"

  private def copy(
      name: String = name,
      content: Frag = content,
      children: List[Section] = children
  ) = new Section(name, content, children)

}

object Section:
  def apply(name: String, content: Frag)(using owner: RelativePath): Section =
    new Section(name, content, List.empty)

  def apply(name: String, content: Frag, children: List[Section])(using
      owner: RelativePath
  ): Section =
    new Section(name, content, children)
