package ba.sake.hepek.html

final class Dependency private (
    val file: String,
    val version: String,
    val pkg: String,
    val baseFolder: Option[String], // usually "dist/", MUST end with slash!
    qParamsString: Option[String]
) {
  val queryParams: String = qParamsString.map(q => "?" + q).getOrElse("")

  def withBaseFolder(baseFolder: String) = copy(baseFolder = Some(baseFolder))

  def withQueryParams(queryParams: String) = copy(qParamsString = Some(queryParams))

  private def copy(
      file: String = file,
      version: String = version,
      pkg: String = pkg,
      baseFolder: Option[String] = baseFolder,
      qParamsString: Option[String] = qParamsString
  ) = new Dependency(file, version, pkg, baseFolder, qParamsString)
}

object Dependency:
  def apply(file: String, version: String, pkg: String): Dependency =
    new Dependency(file, version, pkg, None, None)
