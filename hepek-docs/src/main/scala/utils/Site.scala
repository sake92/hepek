package utils

import ba.sake.hepek.html.statik.StaticPage

object Site:

  val url          = "https://sake92.github.io/hepek"
  val name         = "Hepek"
  val hepekVersion = "0.33.0"

  val allSearchIndexedPages: Seq[StaticPage] = Seq(files.Index) ++
    files.hepek.components.Index.categoryPosts ++
    files.hepek.Index.categoryPosts ++
    files.integrations.Index.categoryPosts
