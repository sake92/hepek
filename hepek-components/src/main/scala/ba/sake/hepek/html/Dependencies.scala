package ba.sake.hepek.html
import ba.sake.stone.Wither

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
  /* URLs */
  def appendURLs(addUrls: List[String]) = copy(urls = urls ++ addUrls)
  def appendURLs(addUrls: String*)      = copy(urls = urls ++ addUrls.toList)

  /* inline styles/scripts */
  def appendInlines(addInlines: List[String]) = copy(inlines = inlines ++ addInlines)
  def appendInlines(addInlines: String*)      = copy(inlines = inlines ++ addInlines.toList)

  /* CDN deps */
  def appendDeps(addDeps: List[Dependency]) = copy(deps = deps ++ addDeps)
  def appendDeps(addDeps: Dependency*)      = copy(deps = deps ++ addDeps.toList)
}
