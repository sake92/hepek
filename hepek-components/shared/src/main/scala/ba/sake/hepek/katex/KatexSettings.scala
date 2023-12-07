package ba.sake.hepek.katex

import ba.sake.hepek.html.DependencyProvider
import ba.sake.hepek.html.BaseComponentSettings

final class KatexSettings private (
    val version: String,
    val pkg: String,
    val depsProvider: DependencyProvider,
    val delimitersInline: (String, String),
    val delimitersBlock: (String, String),
    val ignoredTags: List[String]
) extends BaseComponentSettings {

  def withVersion(version: String): KatexSettings = copy(version = version)

  def withPkg(pkg: String): KatexSettings = copy(pkg = pkg)

  def withDepsProvider(depsProvider: DependencyProvider): KatexSettings =
    copy(depsProvider = depsProvider)

  def withDelimitersInline(delimitersInline: (String, String)): KatexSettings =
    copy(delimitersInline = delimitersInline)

  def withDelimitersBlock(delimitersBlock: (String, String)): KatexSettings =
    copy(delimitersBlock = delimitersBlock)

  def withIgnoredTags(ignoredTags: List[String]): KatexSettings = copy(ignoredTags = ignoredTags)

  def withIgnoredTags(ignoredTags: String*): KatexSettings = copy(ignoredTags = ignoredTags.toList)

  private def copy(
      version: String = version,
      pkg: String = pkg,
      depsProvider: DependencyProvider = depsProvider,
      delimitersInline: (String, String) = delimitersInline,
      delimitersBlock: (String, String) = delimitersBlock,
      ignoredTags: List[String] = ignoredTags
  ) = new KatexSettings(version, pkg, depsProvider, delimitersInline, delimitersBlock, ignoredTags)
}

object KatexSettings:
  def apply(
      version: String,
      pkg: String
  ): KatexSettings = new KatexSettings(
    version,
    pkg,
    DependencyProvider.cdnjs,
    delimitersInline = ("´", "´"),
    delimitersBlock = ("$$", "$$"),
    ignoredTags = List("script", "noscript", "style", "textarea", "pre", "code")
  )
