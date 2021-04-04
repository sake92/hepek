package ba.sake.hepek.html
import ba.sake.kalem.Wither

final case class Dependency(
    file: String,
    version: String,
    pkg: String,
    baseFolder: Option[String] = None, // usually "dist/", MUST end with slash!
    qParams: Option[String] = None
) {
  def queryParams: String = qParams.map(q => "?" + q).getOrElse("")
}

@Wither
final case class Dependencies(
    urls: List[String] = List.empty,
    inlines: List[String] = List.empty,
    deps: List[Dependency] = List.empty
) {
  def plusURLs(addUrls: List[String]) = copy(urls = urls ++ addUrls)
  def plusURLs(addUrls: String*)      = copy(urls = urls ++ addUrls.toList)

  def plusInlines(addInlines: List[String]) = copy(inlines = inlines ++ addInlines)
  def plusInlines(addInlines: String*)      = copy(inlines = inlines ++ addInlines.toList)

  def plusDeps(addDeps: List[Dependency]) = copy(deps = deps ++ addDeps)
  def plusDeps(addDeps: Dependency*)      = copy(deps = deps ++ addDeps.toList)

  def withUrls(urls: List[String]): Dependencies =
    new Dependencies(urls = urls, inlines = inlines, deps = deps)

  def withUrls(urls: String*): Dependencies =
    new Dependencies(inlines = inlines, deps = deps, urls = urls.toList)

  def withInlines(inlines: List[String]): Dependencies =
    new Dependencies(urls = urls, inlines = inlines, deps = deps)

  def withInlines(inlines: String*): Dependencies =
    new Dependencies(urls = urls, deps = deps, inlines = inlines.toList)

  def withDeps(deps: List[Dependency]): Dependencies =
    new Dependencies(urls = urls, inlines = inlines, deps = deps)

  def withDeps(deps: Dependency*): Dependencies =
    new Dependencies(urls = urls, inlines = inlines, deps = deps.toList)
}
