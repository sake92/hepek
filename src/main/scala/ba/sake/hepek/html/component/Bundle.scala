package ba.sake.hepek.html.component

import ba.sake.hepek.html.structure.StaticPage
import ba.sake.hepek.html.structure.blog.BlogPostPage

trait Bundle {

  type Grid <: GridComponents
  type Form <: FormComponents

  type StatikPage <: StaticPage
}
