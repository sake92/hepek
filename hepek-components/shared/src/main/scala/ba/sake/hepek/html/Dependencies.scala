package ba.sake.hepek.html

final class Dependencies private (
    val urls: List[String],
    val inlines: List[String],
    val deps: List[Dependency]
) {
  def plusURLs(addUrls: List[String]) = copy(urls = urls ++ addUrls)
  def plusURLs(addUrls: String*)      = copy(urls = urls ++ addUrls.toList)

  def plusInlines(addInlines: List[String]) = copy(inlines = inlines ++ addInlines)
  def plusInlines(addInlines: String*)      = copy(inlines = inlines ++ addInlines.toList)

  def plusDeps(addDeps: List[Dependency]) = copy(deps = deps ++ addDeps)
  def plusDeps(addDeps: Dependency*)      = copy(deps = deps ++ addDeps.toList)

  def withUrls(urls: List[String]): Dependencies = copy(urls = urls)
  def withUrls(urls: String*): Dependencies      = withUrls(urls.toList)

  def withInlines(inlines: List[String]): Dependencies =
    copy(inlines = inlines)

  def withInlines(inlines: String*): Dependencies = withInlines(inlines.toList)

  def withDeps(deps: List[Dependency]): Dependencies = copy(deps = deps)

  def withDeps(deps: Dependency*): Dependencies = withDeps(deps.toList)

  private def copy(
      urls: List[String] = urls,
      inlines: List[String] = inlines,
      deps: List[Dependency] = deps
  ) = new Dependencies(urls, inlines, deps)
}

object Dependencies:
  val default: Dependencies = new Dependencies(List.empty, List.empty, List.empty)
