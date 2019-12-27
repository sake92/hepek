package ba.sake.hepek.html

final case class Dependency(
    file: String,
    version: String,
    pkg: String,
    baseFolder: Option[String] = None, // usually "dist/", MUST end with slash!
    qParams: Option[String] = None
) {
  def queryParams: String = qParams.map(q => "?" + q).getOrElse("")
}

final case class Dependencies(
    urls: List[String] = List.empty,
    inlines: List[String] = List.empty,
    deps: List[Dependency] = List.empty
) {
  /* URLs */
  def withURLs(newUrls: List[String])   = copy(urls = newUrls)
  def withURLs(newUrls: String*)        = copy(urls = newUrls.toList)
  def appendURLs(addUrls: List[String]) = copy(urls = urls ++ addUrls)
  def appendURLs(addUrls: String*)      = copy(urls = urls ++ addUrls.toList)

  /* inline styles/scripts */
  def withInlines(newInlines: List[String])   = copy(inlines = newInlines)
  def withInlines(newInlines: String*)        = copy(inlines = newInlines.toList)
  def appendInlines(addInlines: List[String]) = copy(inlines = inlines ++ addInlines)
  def appendInlines(addInlines: String*)      = copy(inlines = inlines ++ addInlines.toList)

  /* CDN deps */
  def withDeps(newDeps: List[Dependency])   = copy(deps = newDeps)
  def withDeps(newDeps: Dependency*)        = copy(deps = newDeps.toList)
  def appendDeps(addDeps: List[Dependency]) = copy(deps = deps ++ addDeps)
  def appendDeps(addDeps: Dependency*)      = copy(deps = deps ++ addDeps.toList)
}
